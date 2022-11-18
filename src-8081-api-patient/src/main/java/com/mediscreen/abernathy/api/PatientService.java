package com.mediscreen.abernathy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository repository;
    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient add(PatientDTO dto) {
        var patient = dto.toEntity();
        patient.id = null;
        return repository.save(patient);
    }
}
