package com.example.vsapi.domain.entity.use_case;

import com.example.vsapi.adapter.VideoRepository;
import com.example.vsapi.domain.entity.Video;
import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.use_case.VideoUseCase;
import com.example.vsapi.dto.output.MetadataDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class VideoUseCaseTest {


    private VideoRepository videoRepository;
    private VideoUseCase videoUseCase;

    @BeforeEach
    public void setUp() {
        videoRepository = Mockito.mock(VideoRepository.class);
        videoUseCase = new VideoUseCase(videoRepository);

    }

    @Test
    public void testIncreaseImpressions_Success() {
        Video mockVideo = new Video();
        mockVideo.setImpressions(1L);// Create a mock Video object
        Mockito.when(videoRepository.getVideoById(Mockito.anyLong())).thenReturn(mockVideo);

        videoUseCase.setVideo(1L);
        videoUseCase.increaseImpressions();

        // Ensure that increaseImpressions() method works correctly
        Mockito.verify(videoRepository, Mockito.times(1)).merge(Mockito.any());
        assertEquals(2L, mockVideo.getImpressions());
    }

    @Test
    public void testIncreaseViews_Success() {
        Video mockVideo = new Video(); // Create a mock Video object
        mockVideo.setViews(1L);// Create a mock Video object

        Mockito.when(videoRepository.getVideoById(Mockito.anyLong())).thenReturn(mockVideo);

        videoUseCase.setVideo(1L);
        videoUseCase.increaseViews();

        // Ensure that increaseViews() method works correctly
        Mockito.verify(videoRepository, Mockito.times(1)).merge(Mockito.any());
        assertEquals(2L, mockVideo.getViews());

    }

    @Test
    public void testLoadVideo_Success() {
        Video mockVideo = new Video();
        mockVideo.setImpressions(1L);
        mockVideo.setMetadata(new Metadata());

        Mockito.when(videoRepository.getVideoById(Mockito.anyLong())).thenReturn(mockVideo);
        videoUseCase.setVideo(1L);
        Video video = videoUseCase.loadVideo();

        // Ensure that the returned VideoMetadata is not null
        Assertions.assertNotNull(video);
    }

    @Test
    public void testPlayVideo_Success() {
        Video mockVideo = new Video();

        mockVideo.setViews(1L);

        Mockito.when(videoRepository.getVideoById(Mockito.anyLong())).thenReturn(mockVideo);
        videoUseCase.setVideo(1L);

        Video video = videoUseCase.playVideo();

        // Ensure that the returned Video is not null
        Assertions.assertNotNull(video);
    }

    @Test
    public void testIncreaseImpressionsWhenFindVideoIsNull() {
        Mockito.when(videoRepository.getVideoById(Mockito.anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class,
                () -> videoUseCase.setVideo(1L));
    }
}