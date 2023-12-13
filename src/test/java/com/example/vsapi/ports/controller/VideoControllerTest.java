package com.example.vsapi.ports.controller;

import com.example.vsapi.dto.output.MetadataDTO;
import com.example.vsapi.ports.services.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.vsapi.utils.TemplateMetadataDTO.createSampleMetadata;
import static com.example.vsapi.utils.TemplateMetadataDTO.createSampleMetadataVideo;
import static com.example.vsapi.utils.Utils.asJsonString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideoController.class)
@AutoConfigureMockMvc
public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @Test
    public void testDeleteVideoById() throws Exception {
        Long videoId = 1L; // Replace with an existing video ID in your database

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/videos/{id}", videoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

         verify(videoService, times(1)).deleteVideo(videoId);
    }


    @Test
    public void testLoadVideoById() throws Exception {
        Long videoId = 1L; // Replace with an existing video ID in your database
        MetadataDTO sampleMetadata = createSampleMetadataVideo();

        when(videoService.loadVideo(videoId)).thenReturn(sampleMetadata);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/videos/load/{id}", videoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(sampleMetadata)));
    }

    @Test
    public void testPublishVideo() throws Exception {
        String videoJson = "{\"title\":\"Sample Video\", \"url\":\"http://example.com/sample_video.mp4\", \"impressions\":100, \"views\":50}";
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(videoJson))
                .andExpect(status().isOk());
    }
}