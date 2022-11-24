package com.mediscreen.abernathy.risk;

import com.mediscreen.abernathy.risk.exception.PatientNotFoundException;
import com.mediscreen.abernathy.risk.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    PatientService patientService;

    @Test
    void when_find_by_id_then_fail() {
        Executable action = () -> patientService.read(1L);
        assertThrows(PatientNotFoundException.class, action);
    }

    @Test
    void when_find_by_surname_then_fail() {
        Executable action = () -> patientService.read("familyname");
        assertThrows(PatientNotFoundException.class, action);
    }

}
