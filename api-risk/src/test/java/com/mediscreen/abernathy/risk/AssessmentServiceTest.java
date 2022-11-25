package com.mediscreen.abernathy.risk;

import com.mediscreen.abernathy.risk.domain.PatientProfile;
import com.mediscreen.abernathy.risk.domain.RiskLevel;
import com.mediscreen.abernathy.risk.domain.Sex;
import com.mediscreen.abernathy.risk.service.AssessmentService;
import com.mediscreen.abernathy.risk.service.PatientProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mediscreen.abernathy.risk.domain.Sex.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssessmentServiceTest {

    @InjectMocks
    AssessmentService assessmentService;

    @Mock
    PatientProfileService patientProfileService;

    @Test
    void when_read_by_id_then_success() {
        var patient = new PatientProfile("name","surname", 18, MALE, 1);
        when(patientProfileService.readById(1L)).thenReturn(patient);
        var result = assessmentService.getReport(1L);
        assertEquals("name", result.given);
        assertEquals("surname", result.family);
        assertEquals(18, result.age);
    }

    @Test
    void when_read_by_family_name_then_success() {
        var patient = new PatientProfile("name","surname", 18, MALE, 1);
        when(patientProfileService.readByFamilyName("surname")).thenReturn(patient);
        var result = assessmentService.getReport("surname");
        assertEquals("name", result.given);
        assertEquals("surname", result.family);
        assertEquals(18, result.age);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/diabetes-assess.csv", numLinesToSkip = 1)
    void when_assess_then_correct_risk(int age, Sex sex, int count, RiskLevel risk) {
        var patient = new PatientProfile("a","b", age, sex,count);
        when(patientProfileService.readById(1L)).thenReturn(patient);
        var result = assessmentService.getReport(1L);
        assertEquals(risk, result.diabetesRisk);
    }

}
