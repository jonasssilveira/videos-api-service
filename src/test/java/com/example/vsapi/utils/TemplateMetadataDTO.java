package com.example.vsapi.utils;

import com.example.vsapi.dto.output.MetadataDTO;

import java.time.LocalDateTime;

public class TemplateMetadataDTO {
    public static MetadataDTO createSampleMetadata() {
        return MetadataDTO.builder()
                .title("Example Title 1")
                .runningTime(120)
                .director("John Doe")
                .mainActor("Jane Smith")
                .synopsis("Example synopsis 1")
                .releaseYear(LocalDateTime.of(2023,1,20,22,10))
                .genre("Action")
                .video("http://example.com/video1.mp4")
                .build();
    }

    public static MetadataDTO createSampleMetadataVideo() {
        return MetadataDTO.builder()
                .title("Example Title 1")
                .runningTime(120)
                .director("John Doe")
                .mainActor("Jane Smith")
                .synopsis("Example synopsis 1")
                .genre("Action")
                .video("http://example.com/video1.mp4")
                .build();
    }

}
