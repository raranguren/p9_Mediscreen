package com.mediscreen.abernathy.risk.service;

import com.mediscreen.abernathy.risk.domain.PatientProfile;
import com.mediscreen.abernathy.risk.domain.RiskLevel;
import com.mediscreen.abernathy.risk.domain.Sex;
import com.mediscreen.abernathy.risk.dto.AssessmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mediscreen.abernathy.risk.domain.RiskLevel.*;
import static com.mediscreen.abernathy.risk.domain.Sex.MALE;

@Service
public class AssessmentService {

    private final PatientProfileService patientService;
    @Autowired
    public AssessmentService(PatientProfileService patientProfileService) {
        this.patientService = patientProfileService;
    }

    public AssessmentDTO getReport(Long patId) {
        return generateReport(patientService.readById(patId));
    }

    public AssessmentDTO getReport(String family) {
        return generateReport(patientService.readByFamilyName(family));
    }

    public AssessmentDTO generateReport(PatientProfile patient) {
        var report = new AssessmentDTO();
        report.given = patient.given;
        report.family = patient.family;
        report.age = patient.age;
        report.diabetesRisk = calculateRisk(patient.age, patient.sex, patient.diabetesTriggerCount);
        return report;
    }

    /* Diabetes risk calculation based on age, sex and number of terms used by doctors (disease triggers)
        +---+-------------+-------------+-------------+
        |   | MALE < 30   | FEMALE < 30 |    >= 30    |
        +---+-------------+-------------+-------------+
        | 0 | NONE        | NONE        | NONE        |
        | 1 |             |             |             |
        | 2 |             |             | BORDERLINE  |
        | 3 | IN DANGER   |             |             |
        | 4 |             | IN DANGER   |             |
        | 5 | EARLY ONSET |             |             |
        | 6 |             |             | IN DANGER   |
        | 7 |             | EARLY ONSET |             |
        | 8 |             |             | EARLY ONSET |
        +---+-------------+-------------+-------------+
     */
    private RiskLevel calculateRisk(Integer age, Sex sex, Integer count) {
        if (count == 0) return NONE;
        if (age >= 30) {
            if (count >= 8) return EARLY_ONSET;
            if (count >= 6) return IN_DANGER;
            if (count >= 2) return BORDERLINE;
        }
        if (sex == MALE) {
            if (count >= 5) return EARLY_ONSET;
            if (count >= 3) return IN_DANGER;
        }
        if (count >= 7) return EARLY_ONSET;
        if (count >= 4) return IN_DANGER;
        return NONE;
    }

}
