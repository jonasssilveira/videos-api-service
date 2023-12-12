package com.example.vsapi.domain.use_case;

import com.example.vsapi.adapter.StaffRepository;
import com.example.vsapi.domain.entity.Staff;
import com.example.vsapi.dto.output.StaffDTO;

public class StaffUseCase {
    
    StaffRepository staffRepository;

    public StaffUseCase(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff getStaffByName(String name, boolean mainActor){
        return staffRepository.getByName(name, mainActor);
    }

    public Staff merge(Staff staff){
        var staffEntity = getStaffByName(staff.getName(), staff.isMainActor());
        if(staffEntity==null){
            staffEntity= new Staff();
        }
        staffEntity.setName(staff.getName());
        staffEntity.setMainActor(staff.isMainActor());
        return staffRepository.merge(staffEntity);
    }


}
