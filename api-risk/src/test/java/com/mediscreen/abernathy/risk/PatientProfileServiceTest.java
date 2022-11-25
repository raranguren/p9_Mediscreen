package com.mediscreen.abernathy.risk;

import com.mediscreen.abernathy.risk.dto.PatientDTO;
import com.mediscreen.abernathy.risk.exception.PatientNotFoundException;
import com.mediscreen.abernathy.risk.proxy.PatientProxy;
import com.mediscreen.abernathy.risk.service.NoteAnalysisService;
import com.mediscreen.abernathy.risk.service.PatientProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientProfileServiceTest {

    @InjectMocks
    PatientProfileService patientProfileService;

    @Mock
    PatientProxy patientProxy;

    @Mock
    NoteAnalysisService noteAnalysisService;

    @Test
    void when_find_by_id_then_success() {
        var patient = new PatientDTO();
        patient.id = 1L;
        patient.given = "correct";
        when(patientProxy.findById(anyLong())).thenReturn(Optional.of(patient));
        var result = patientProfileService.readById(1L);
        assertEquals("correct", result.given);
    }

    @Test
    void when_find_by_surname_then_success() {
        var patient = new PatientDTO();
        patient.id = 1L;
        patient.given = "correct";
        when(patientProxy.findByFamilyName(anyString())).thenReturn(Optional.of(patient));
        var result = patientProfileService.readByFamilyName("familyname");
        assertEquals("correct", result.given);
    }

    @Test
    void when_find_by_id_then_fail() {
        Executable action = () -> patientProfileService.readById(1L);
        assertThrows(PatientNotFoundException.class, action);
    }

    @Test
    void when_find_by_surname_then_fail() {
        Executable action = () -> patientProfileService.readByFamilyName("familyname");
        assertThrows(PatientNotFoundException.class, action);
    }

    @Test
    void when_calculate_age_then_success() {
        var today = LocalDate.now();
        var tenYearsAgo = today.minusYears(10);
        String dob = tenYearsAgo.format(ofPattern("yyyy-MM-dd"));

        var patient = new PatientDTO();
        patient.id = 1L;
        patient.dob = dob;

        when(patientProxy.findById(anyLong())).thenReturn(Optional.of(patient));
        var result = patientProfileService.readById(1L);

        assertEquals(10, result.age);
    }

}
