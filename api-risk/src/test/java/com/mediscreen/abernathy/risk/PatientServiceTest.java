package com.mediscreen.abernathy.risk;

import com.mediscreen.abernathy.risk.domain.Patient;
import com.mediscreen.abernathy.risk.exception.PatientNotFoundException;
import com.mediscreen.abernathy.risk.proxy.NotesProxy;
import com.mediscreen.abernathy.risk.proxy.PatientProxy;
import com.mediscreen.abernathy.risk.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    PatientService patientService;

    @Mock
    PatientProxy patientProxy;

    @Mock
    NotesProxy notesProxy;

    @Test
    void when_find_by_id_then_success() {
        var patient = new Patient();
        patient.id = 1L;
        patient.given = "correct";
        when(patientProxy.findById(anyLong())).thenReturn(Optional.of(patient));
        when(notesProxy.readNotesByPatientId(anyLong())).thenReturn(emptyList());
        var result = patientService.readById(1L);
        assertEquals("correct", result.given);
    }

    @Test
    void when_find_by_surname_then_success() {
        var patient = new Patient();
        patient.id = 1L;
        patient.given = "correct";
        when(patientProxy.findByFamilyName(anyString())).thenReturn(Optional.of(patient));
        when(notesProxy.readNotesByPatientId(anyLong())).thenReturn(emptyList());
        var result = patientService.readByFamilyName("familyname");
        assertEquals("correct", result.given);
    }

    @Test
    void when_find_by_id_then_fail() {
        Executable action = () -> patientService.readById(1L);
        assertThrows(PatientNotFoundException.class, action);
    }

    @Test
    void when_find_by_surname_then_fail() {
        Executable action = () -> patientService.readByFamilyName("familyname");
        assertThrows(PatientNotFoundException.class, action);
    }

}
