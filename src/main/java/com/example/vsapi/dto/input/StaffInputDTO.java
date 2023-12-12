package com.example.vsapi.dto.input;

import com.example.vsapi.domain.entity.Staff;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@ApiModel(description = "DTO for staff input")
public class StaffInputDTO {

    public Staff staffDTOToStaffEntity() {
        return Staff.builder()
                .mainActor(mainActor)
                .name(name)
                .build();
    }

    @Getter
    @Setter
    @ApiModelProperty(notes = "Name of the staff member")
    private String name;

    @Getter
    @Setter
    @JsonProperty("main_actor")
    @ApiModelProperty(notes = "Indicates whether the staff member is a main actor")
    private boolean mainActor;
}
