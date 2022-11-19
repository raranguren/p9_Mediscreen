package com.mediscreen.abernathy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository repository;
    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public PatientDTO add(PatientDTO dto) {
        var patient = dto.toEntity();
        patient.id = null;
        patient = repository.save(patient);
        return new PatientDTO(patient);
    }

    public List<PatientDTO> readAll() {
        return repository.findAll()
                .stream().map(PatientDTO::new)
                .collect(Collectors.toList());
    }

}
