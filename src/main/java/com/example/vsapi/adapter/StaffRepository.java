package com.example.vsapi.adapter;

import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Staff;

import java.util.List;

public interface StaffRepository {
    Staff getMainActor();

    Staff getByName(String name, boolean mainActor);

    Staff merge(Staff staff);
}
