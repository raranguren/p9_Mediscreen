package com.mediscreen.abernathy.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository repository;
    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public PatientDTO create(PatientDTO dto) {
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

    public PatientDTO update(PatientDTO dto) {
        if (dto.id == null || !repository.existsById(dto.id)) return null;
        Patient patient = dto.toEntity();
        patient = repository.save(patient);
        return new PatientDTO(patient);
    }

    public PatientDTO delete(Long id) {
        var search = repository.findById(id);
        if (search.isEmpty()) return null;
        Patient patient = search.get();
        repository.delete(patient);
        return new PatientDTO(patient);
    }

    public Optional<PatientDTO> read(Long id) {
        var patient = repository.findById(id);
        if (patient.isEmpty()) return Optional.empty();
        return Optional.of(new PatientDTO(patient.get()));
    }
}
