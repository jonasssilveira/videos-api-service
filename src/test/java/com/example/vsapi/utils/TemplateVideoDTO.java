package com.example.vsapi.utils;

import com.example.vsapi.dto.output.VideoDTO;

public class TemplateVideoDTO {

    public static VideoDTO createTemplate(){
        return VideoDTO.builder().video("test video")
                .impressions(10L)
                .views(10L)
                .build();
    }

}
