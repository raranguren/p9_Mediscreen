package com.mediscreen.abernathy.risk.service;

import com.mediscreen.abernathy.risk.domain.Patient;
import com.mediscreen.abernathy.risk.exception.PatientNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    public Patient read(Long patId) {
        // TODO read a patient and fill its notes
        throw new PatientNotFoundException();
    }

    public Patient read(String family) {
        // TODO
        throw new PatientNotFoundException();
    }



}
