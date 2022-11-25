package com.mediscreen.abernathy.risk.service;

import com.mediscreen.abernathy.risk.domain.PatientProfile;
import com.mediscreen.abernathy.risk.dto.PatientDTO;
import com.mediscreen.abernathy.risk.exception.PatientNotFoundException;
import com.mediscreen.abernathy.risk.proxy.PatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.Period;

import static com.mediscreen.abernathy.risk.service.NoteAnalysisService.DIABETES_TRIGGERS;
import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;

@Service
public class PatientProfileService {

    private final PatientProxy patientProxy;
    private final NoteAnalysisService noteAnalysisService;
    @Autowired
    public PatientProfileService(PatientProxy patientProxy, NoteAnalysisService noteAnalysisService) {
        this.patientProxy = patientProxy;
        this.noteAnalysisService = noteAnalysisService;
    }

    public PatientProfile readById(Long patId) {
        var patient = this.patientProxy.findById(patId)
                .orElseThrow(PatientNotFoundException::new);
        return generateProfileForPatient(patient);
    }

    public PatientProfile readByFamilyName(String family) {
        var patient = this.patientProxy.findByFamilyName(family)
                .orElseThrow(PatientNotFoundException::new);
        return generateProfileForPatient(patient);
    }

    private PatientProfile generateProfileForPatient(PatientDTO patient) {
        var profile = new PatientProfile();
        profile.family = patient.family;
        profile.given = patient.given;
        profile.age = ageFromDateString(patient.dob);
        profile.diabetesTriggerCount = noteAnalysisService.getTriggerCount(patient.id, DIABETES_TRIGGERS);
        return profile;
    }

    private Integer ageFromDateString(String dob) {
        try {
            return Period.between(parse(dob),now()).getYears();
        } catch (DateTimeException | NullPointerException e) {
            return null;
        }
    }

}
