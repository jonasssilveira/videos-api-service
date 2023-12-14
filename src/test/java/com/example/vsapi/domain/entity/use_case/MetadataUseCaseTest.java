package com.example.vsapi.domain.entity.use_case;

import com.example.vsapi.adapter.MetadataRepository;
import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Staff;
import com.example.vsapi.domain.use_case.MetadataUseCase;
import com.example.vsapi.domain.use_case.StaffUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MetadataUseCaseTest {
    @Mock
    private MetadataRepository metadataRepository;

    @Mock
    private StaffUseCase staffUseCase;

    @InjectMocks
    private MetadataUseCase metadataUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllVideos() {
        // Mock the behavior of metadataRepository.getAllVideos()
        List<Metadata> metadataList = new ArrayList<>();
        when(metadataRepository.getAllVideos()).thenReturn(metadataList);

        // Call the method to be tested
        List<Metadata> result = metadataUseCase.listAllVideos();

        // Verify the result
        assertEquals(metadataList, result);
    }

    @Test
    public void testMergeMetadata() {
        // Mock the input metadata
        Metadata metadata = new Metadata();
        metadata.setStaffMembers(List.of(new Staff(1L,"test", true)));
        // Mock the behavior of metadataRepository.merge()
        when(metadataRepository.merge(any())).thenReturn(metadata);

        // Mock the behavior of staffUseCase.merge()
        when(staffUseCase.merge(any())).thenReturn(new Staff());

        // Call the method to be tested
        Metadata result = metadataUseCase.mergeMetadata(metadata);

        // Verify that the metadata and staffUseCase merge method were called
        verify(metadataRepository, times(1)).merge(any());
        verify(staffUseCase, times(1)).merge(any());

        // Assert the result
        assertNotNull(result);
        // Add further assertions as needed
    }

    @Test
    public void testSetMetadata_whenMetadataExists() {
        // Mock metadataId
        Long metadataId = 1L;

        // Mock the behavior of metadataRepository.getMetadataById()
        Metadata metadata = new Metadata();
        when(metadataRepository.getMetadataById(metadataId)).thenReturn(metadata);

        // Call the method to be tested
        metadataUseCase.setMetadata(metadataId);

        // Verify that metadata is set
        assertNotNull(metadataUseCase.getMetadata());
    }

    @Test
    public void testSetMetadata_whenMetadataDoesNotExist() {
        // Mock metadataId
        Long metadataId = 1L;

        // Mock the behavior of metadataRepository.getMetadataById() returning null
        when(metadataRepository.getMetadataById(metadataId)).thenReturn(null);

        // Call the method to be tested and expect an exception
        assertThrows(RuntimeException.class, () -> metadataUseCase.setMetadata(metadataId));
    }
}