package com.mediscreen.abernathy.risk.service;

import com.mediscreen.abernathy.risk.domain.PatientProfile;
import com.mediscreen.abernathy.risk.domain.RiskLevel;
import com.mediscreen.abernathy.risk.dto.AssessmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AssessmentDTO generateReport(PatientProfile patientProfile) {
        var report = new AssessmentDTO();
        report.given = patientProfile.given;
        report.family = patientProfile.family;
        report.age = patientProfile.age;
        report.diabetesRisk = RiskLevel.RISK_NONE; // TODO calculateRisk()
        return report;
    }

}
