package com.example.vsapi.ports.controller;

import com.example.vsapi.dto.input.MetadataInputDTO;
import com.example.vsapi.dto.output.MetadataDTO;
import com.example.vsapi.ports.services.MetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.is;
import static com.example.vsapi.utils.TemplateMetadataDTO.createSampleMetadata;
import static com.example.vsapi.utils.Utils.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetadataController.class)
@AutoConfigureMockMvc
public class MetadataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetadataService metadataService;

    @Test
    public void testGetAllMetadataSuccess() throws Exception {
        List<MetadataDTO> metadataDTOList = new ArrayList<>();
        when(metadataService.listVideosMetadata()).thenReturn((ResponseEntity.ok(metadataDTOList)));
        mockMvc.perform(get("/v1/metadata")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllMetadataNoContent() throws Exception {
        when(metadataService.listVideosMetadata()).thenReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT));
        mockMvc.perform(get("/v1/metadata")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllByQuerySuccess() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("director", "John Doe");

        when(metadataService.getMetadataByQuery(queryParams))
                .thenReturn(new ResponseEntity<>(Arrays.asList(createSampleMetadata(),
                        createSampleMetadata()), HttpStatus.OK));

        mockMvc.perform(get("/v1/metadata/q").queryParams(queryParams))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    public void testGetAllByQueryNoContent() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("director", "John Doe");

        when(metadataService.getMetadataByQuery(queryParams))
                .thenReturn(new ResponseEntity<>(Arrays.asList(), HttpStatus.NO_CONTENT));

        mockMvc.perform(get("/v1/metadata/q").queryParams(queryParams))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }


    @Test
    public void testMetadataById() throws Exception {

        MetadataDTO sampleMetadata = createSampleMetadata();
        when(metadataService.getMetadataById(1L))
                .thenReturn(new ResponseEntity<>(createSampleMetadata(), HttpStatus.OK));

        mockMvc.perform(get("/v1/metadata/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue())).andExpect(jsonPath("$.title", is(createSampleMetadata().getTitle())))
                .andExpect(jsonPath("$.director", is(createSampleMetadata().getDirector())))
                .andExpect(jsonPath("$.main_actor", is(createSampleMetadata().getMainActor())))
                .andExpect(jsonPath("$.synopsis", is(createSampleMetadata().getSynopsis())))
                .andExpect(jsonPath("$.genre", is(createSampleMetadata().getGenre())))
                .andExpect(jsonPath("$.video", is(createSampleMetadata().getVideo())));

    }

    @Test
    public void testUpdateMetadata() throws Exception {
        MetadataInputDTO updatedInputDTO = MetadataInputDTO.builder().title("Updated title").build();

        Long metadataId = 1L;
        when(metadataService.mergeMetadata(eq(metadataId), any(MetadataInputDTO.class)))
                .thenReturn(new ResponseEntity<>(createSampleMetadata(), HttpStatus.OK));

        var updatedMetadataDTO = createSampleMetadata();
        mockMvc.perform(put("/v1/metadata/{id}", metadataId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedInputDTO)))
                .andExpect(status().isOk());

        when(metadataService.getMetadataById(metadataId))
                .thenReturn(new ResponseEntity<>(updatedMetadataDTO, HttpStatus.OK));

        mockMvc.perform(get("/v1/metadata/{id}", metadataId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(updatedMetadataDTO.getTitle())));
    }

}