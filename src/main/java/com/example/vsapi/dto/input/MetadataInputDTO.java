package com.example.vsapi.dto.input;

import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Staff;
import com.example.vsapi.domain.entity.Video;
import com.example.vsapi.dto.output.StaffDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "DTO for metadata input")
public class MetadataInputDTO {

    public Metadata metadataDTOToEntity(Long id) {
        return Metadata.builder()
                .id(id)
                .createdAt(LocalDateTime.now())
                .runningTime(runningTime)
                .releaseYear(releaseYear)
                .staffMembers(staff.stream().map(StaffInputDTO::staffDTOToStaffEntity).collect(Collectors.toList()))
                .synopsis(synopsis)
                .updatedAt(LocalDateTime.now())
                .director(director)
                .title(title)
                .build();
    }

    @Getter
    @ApiModelProperty(notes = "Title of the metadata")
    private String title;

    @Getter
    @JsonProperty("running_time")
    @ApiModelProperty(notes = "Running time in seconds")
    private long runningTime;

    @Getter
    @ApiModelProperty(notes = "Director of the metadata")
    private String director;

    @JsonProperty("main_actor")
    @Getter
    @ApiModelProperty(notes = "Main actor in the metadata")
    private String mainActor;

    @Getter
    @ApiModelProperty(notes = "Synopsis of the metadata")
    private String synopsis;

    @Getter
    @JsonProperty("release_year")
    @ApiModelProperty(notes = "Release year of the metadata")
    private LocalDateTime releaseYear;

    @Getter
    @ApiModelProperty(notes = "Genre of the metadata")
    private String genre;

    @Getter
    @ApiModelProperty(notes = "Video URL of the metadata")
    private String video;

    @Getter
    @ApiModelProperty(notes = "List of staff members associated with the metadata")
    private List<StaffInputDTO> staff;
}