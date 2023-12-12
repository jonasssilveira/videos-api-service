package com.example.vsapi.dto.output;

import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Staff;
import com.example.vsapi.domain.entity.Video;
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

@ApiModel(description = "DTO for metadata")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetadataDTO {

    public MetadataDTO(Metadata metadata) {
        title = metadata.getTitle();
        synopsis = metadata.getSynopsis();
        releaseYear = metadata.getReleaseYear();
        genre = metadata.getGenre();
        runningTime = metadata.getRunningTime();
        this.director = metadata.getDirector();
        this.mainActor = metadata.getStaffMembers().stream().filter(Staff::isMainActor).map(Staff::getName).findAny().orElse("Unknown Main Character");
        this.staff = metadata.getStaffMembers().stream().map(StaffDTO::new).collect(Collectors.toList());
    }

    public MetadataDTO MetadataDTOList(Metadata metadata) {
        return MetadataDTO.
                builder().
                title(metadata.getTitle()).
                runningTime(
                        metadata.getRunningTime()
                ).
                director(
                        metadata.getDirector()
                ).
                genre(metadata.getGenre()).
                mainActor(metadata.
                        getStaffMembers().
                        stream().
                        filter(Staff::isMainActor).
                        map(Staff::getName).
                        findAny().
                        orElse("Unknown Main Character")
                ).
                build();

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
    @ApiModelProperty(notes = "Casting information")
    private String casting;

    @Getter
    @JsonProperty("release_year")
    @ApiModelProperty(notes = "Release year of the metadata")
    private LocalDateTime releaseYear;

    @Getter
    @ApiModelProperty(notes = "Genre of the metadata")
    private String genre;

    @Getter
    @ApiModelProperty(notes = "Video URL")
    private String video;

    @Getter
    @ApiModelProperty(notes = "List of staff members associated with the metadata")
    private List<StaffDTO> staff;

    @JsonProperty
    @Getter
    @ApiModelProperty(notes = "Number of views")
    private Long views;

    @JsonProperty
    @Getter
    @ApiModelProperty(notes = "Number of impressions")
    private Long impressions;
}
