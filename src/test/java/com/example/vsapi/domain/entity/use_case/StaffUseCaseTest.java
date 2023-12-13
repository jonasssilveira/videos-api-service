package com.example.vsapi.domain.entity.use_case;

import com.example.vsapi.adapter.StaffRepository;
import com.example.vsapi.domain.entity.Staff;
import com.example.vsapi.domain.use_case.StaffUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StaffUseCaseTest {
    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffUseCase staffUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStaffByName() {
        // Mock the behavior of staffRepository.getByName()
        String name = "John";
        boolean mainActor = true;
        Staff expectedStaff = new Staff(); // Create expected staff object
        when(staffRepository.getByName(name, mainActor)).thenReturn(expectedStaff);

        // Call the method to be tested
        Staff result = staffUseCase.getStaffByName(name, mainActor);

        // Verify the result
        assertNotNull(result);
        assertEquals(expectedStaff, result);
    }

    @Test
    public void testMerge_newStaff() {
        // Mock the input staff
        Staff staff = new Staff();
        staff.setName("Jane");
        staff.setMainActor(true);

        // Mock the behavior of staffRepository.getByName() returning null
        when(staffRepository.getByName(anyString(), anyBoolean())).thenReturn(null);

        // Mock the behavior of staffRepository.merge()
        when(staffRepository.merge(any())).thenReturn(staff);

        // Call the method to be tested
        Staff result = staffUseCase.merge(staff);

        // Verify that staffRepository.getByName() and staffRepository.merge() were called
        verify(staffRepository, times(1)).getByName(anyString(), anyBoolean());
        verify(staffRepository, times(1)).merge(any());

        // Assert the result
        assertNotNull(result);
        assertEquals(staff.getName(), result.getName());
        assertEquals(staff.isMainActor(), result.isMainActor());
    }

    @Test
    public void testMerge_existingStaff() {
        // Mock the input staff
        Staff staff = new Staff();
        staff.setName("Jane");
        staff.setMainActor(true);

        // Mock the behavior of staffRepository.getByName() returning an existing staff
        when(staffRepository.getByName(anyString(), anyBoolean())).thenReturn(staff);
        when(staffRepository.merge(staff)).thenReturn(staff);

        // Call the method to be tested
        Staff result = staffUseCase.merge(staff);

        // Verify that staffRepository.getByName() and staffRepository.merge() were called
        verify(staffRepository, times(1)).getByName(anyString(), anyBoolean());
        verify(staffRepository, times(1)).merge(any());

        // Assert the result
        assertNotNull(result);
        assertEquals(staff.getName(), result.getName());
        assertEquals(staff.isMainActor(), result.isMainActor());
    }
}