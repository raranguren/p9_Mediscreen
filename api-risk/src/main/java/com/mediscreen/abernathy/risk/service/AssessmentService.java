package com.mediscreen.abernathy.risk.service;

import com.mediscreen.abernathy.risk.domain.Assessment;
import com.mediscreen.abernathy.risk.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {

    private final PatientService patientService;
    @Autowired
    public AssessmentService(PatientService patientService) {
        this.patientService = patientService;
    }

    public Assessment getReport(Long patId) {
        return getReport(patientService.read(patId));
    }

    public Assessment getReport(String family) {
        return getReport(patientService.read(family));
    }

    public Assessment getReport(Patient patient) {
        // TODO build report based on age, sex and
        return new Assessment();
    }

}
