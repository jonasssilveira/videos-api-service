package com.example.vsapi.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "DTO for video")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    @Getter
    @Setter
    @JsonProperty
    @ApiModelProperty(notes = "Video URL")
    private String video;

    @Getter
    @Setter
    @JsonProperty
    @ApiModelProperty(notes = "Number of impressions")
    private Long impressions;

    @Getter
    @Setter
    @JsonProperty
    @ApiModelProperty(notes = "Number of views")
    private Long views;
}

