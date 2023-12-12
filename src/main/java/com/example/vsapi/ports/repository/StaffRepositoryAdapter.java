package com.example.vsapi.ports.repository;

import com.example.vsapi.adapter.StaffRepository;
import com.example.vsapi.domain.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepositoryAdapter implements StaffRepository {

    final StaffJpaRepository staffRepository;

    public StaffRepositoryAdapter(@Autowired StaffJpaRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff getMainActor() {
        return staffRepository.getBymainActorTrue().orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public Staff getByName(String name, boolean mainActor) {
        return staffRepository.getStaffByName(name).orElse(null);
    }

    @Override
    public Staff merge(Staff staff) {
        return staffRepository.saveAndFlush(staff);
    }


}
