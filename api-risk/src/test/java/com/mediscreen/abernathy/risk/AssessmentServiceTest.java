package com.mediscreen.abernathy.risk;

import com.mediscreen.abernathy.risk.domain.Patient;
import com.mediscreen.abernathy.risk.service.AssessmentService;
import com.mediscreen.abernathy.risk.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssessmentServiceTest {

    @InjectMocks
    AssessmentService assessmentService;

    @Mock
    PatientService patientService;

    @Test
    void when_read_by_id_then_success() {
        when(patientService.read(1L)).thenReturn(new Patient());
        var result = assessmentService.getReport(1L);
        assertNotNull(result);
    }

    @Test
    void when_read_by_family_name_then_success() {
        when(patientService.read("familyname")).thenReturn(new Patient());
        var result = assessmentService.getReport("familyname");
        assertNotNull(result);
    }

}
