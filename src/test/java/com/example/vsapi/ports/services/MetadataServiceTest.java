package com.example.vsapi.ports.services;

import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Staff;
import com.example.vsapi.dto.input.MetadataInputDTO;
import com.example.vsapi.dto.input.StaffInputDTO;
import com.example.vsapi.dto.output.MetadataDTO;
import com.example.vsapi.ports.exceptions.NoContentRequestException;
import com.example.vsapi.ports.repository.MetadataRepositoryAdapter;
import com.example.vsapi.ports.repository.StaffRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class MetadataServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private MetadataRepositoryAdapter metadataRepository;

    @Mock
    private StaffRepositoryAdapter staffRepository;

    @InjectMocks
    private MetadataService metadataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        metadataService = new MetadataService(metadataRepository, staffRepository);
    }

    @Test
    void testListVideosMetadataWhenNoContent() {
        // Mock behavior of metadataUseCase.listAllVideos() method
        when(metadataService.metadataUseCase.listAllVideos()).thenReturn(Collections.emptyList());

        assertThrows(NoContentRequestException.class, () -> metadataService.listVideosMetadata());
    }

    @Test
    void testListVideosMetadataWhenSuccess() {
        Metadata metadata = new Metadata();
        metadata.setStaffMembers(List.of(new Staff()));
        when(metadataService.metadataUseCase.listAllVideos()).thenReturn(List.of(metadata));

        // Test the method
        ResponseEntity<List<MetadataDTO>> response = metadataService.listVideosMetadata();

        // Assertions
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }


    @Test
    void testGetMetadataByIdSuccess() {
        // Mock data
        Long metadataId = 1L;
        Long id = 1L;
        MetadataInputDTO inputDTO = MetadataInputDTO
                .builder()
                .staff(List
                        .of(new StaffInputDTO()))
                .build(); // Assuming you have a MetadataInputDTO class

        Metadata metadata = new Metadata(); // Assuming you have a Metadata class
        metadata.setStaffMembers(List.of(new Staff()));
        when(metadataRepository.getMetadataById(1L)).thenReturn(metadata);
        when(metadataRepository.merge(any())).thenReturn(metadata);
        // Call the method
        ResponseEntity<MetadataDTO> responseEntity = metadataService.getMetadataById(metadataId);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue()); // Check for HTTP status code 200 (OK)
        // Add more assertions based on the behavior you expect from the method
    }

    @Test
    void testMergeMetadataSuccess() {
        // Mock data
        Long id = 1L;
        MetadataInputDTO inputDTO = MetadataInputDTO
                .builder()
                .staff(List
                        .of(new StaffInputDTO()))
                .build(); // Assuming you have a MetadataInputDTO class

        Metadata metadata = new Metadata(); // Assuming you have a Metadata class
        metadata.setStaffMembers(List.of(new Staff()));
        when(metadataRepository.getMetadataById(1L)).thenReturn(metadata);
        when(metadataRepository.merge(any())).thenReturn(metadata);

        // Call the method'
        ResponseEntity<MetadataDTO> responseEntity = metadataService.mergeMetadata(id, inputDTO);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue()); // Check for HTTP status code 200 (OK)
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getStaff().size()); // Check for HTTP status code 200 (OK)

    }

    @Test
    void testMergeMetadata() {
        // Mock data
        Long id = 1L;
        MetadataInputDTO inputDTO = MetadataInputDTO
                .builder()
                .staff(List
                        .of(new StaffInputDTO()))
                .build(); // Assuming you have a MetadataInputDTO class

        Metadata metadata = new Metadata(); // Assuming you have a Metadata class
        metadata.setStaffMembers(List.of(new Staff()));
        when(metadataRepository.getMetadataById(1L)).thenReturn(metadata);
        when(metadataRepository.merge(any())).thenReturn(metadata);

        // Call the method'
        ResponseEntity<MetadataDTO> responseEntity = metadataService.mergeMetadata(id, inputDTO);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue()); // Check for HTTP status code 200 (OK)
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getStaff().size()); // Check for HTTP status code 200 (OK)

    }

}