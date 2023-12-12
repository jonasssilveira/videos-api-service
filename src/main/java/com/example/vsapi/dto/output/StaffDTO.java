package com.example.vsapi.dto.output;

import com.example.vsapi.domain.entity.Staff;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "DTO for staff")
public class StaffDTO {

    public StaffDTO(Staff staff) {
        name = staff.getName();
        mainActor = staff.isMainActor();
    }

    @Getter
    @JsonProperty
    @ApiModelProperty(notes = "Name of the staff member")
    private String name;

    @Getter
    @JsonProperty("main_actor")
    @ApiModelProperty(notes = "Indicates whether the staff member is a main actor")
    private boolean mainActor;

}
