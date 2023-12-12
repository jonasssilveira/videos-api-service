package com.example.vsapi.ports.repository;

import com.example.vsapi.domain.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffJpaRepository  extends JpaRepository<Staff, Long> {

    Optional<Staff> getBymainActorTrue();
    Optional<Staff> getStaffByName(String name);
}
