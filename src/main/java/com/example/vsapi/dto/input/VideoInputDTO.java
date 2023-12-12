package com.example.vsapi.dto.input;

import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Video;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@ApiModel(description = "DTO for video input")
@NoArgsConstructor
@AllArgsConstructor
public class VideoInputDTO {

    public Video videoDTOToEntity() {
        return Video.builder()
                .video(video)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deleted(false)
                .views(0L)
                .impressions(0L)
                .build();
    }

    public Metadata metadataDTOToEntity() {
        return Metadata.builder()
                .createdAt(LocalDateTime.now())
                .runningTime(runningTime)
                .releaseYear(releaseYear)
                .genre(genre)
                .staffMembers(staff.stream().map(StaffInputDTO::staffDTOToStaffEntity).collect(Collectors.toList()))
                .synopsis(synopsis)
                .updatedAt(LocalDateTime.now())
                .director(director)
                .title(title)
                .build();
    }

    @Getter
    @ApiModelProperty(notes = "Title of the video")
    private String title;

    @Getter
    @JsonProperty("running_time")
    @ApiModelProperty(notes = "Running time in seconds")
    private long runningTime;

    @Getter
    @ApiModelProperty(notes = "Director of the video")
    private String director;

    @JsonProperty("main_actor")
    @Getter
    @ApiModelProperty(notes = "Main actor in the video")
    private String mainActor;

    @Getter
    @ApiModelProperty(notes = "Synopsis of the video")
    private String synopsis;

    @Getter
    @JsonProperty("release_year")
    @ApiModelProperty(notes = "Release year of the video")
    private LocalDateTime releaseYear;

    @Getter
    @ApiModelProperty(notes = "Genre of the video")
    private String genre;

    @Getter
    @ApiModelProperty(notes = "Video URL")
    @Setter
    private String video;

    @Getter
    @ApiModelProperty(notes = "List of staff members associated with the video")
    private List<StaffInputDTO> staff;
}
