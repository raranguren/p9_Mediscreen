package com.mediscreen.abernathy.web;

import com.mediscreen.abernathy.web.dto.Patient;
import com.mediscreen.abernathy.web.proxy.ApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final ApiProxy api;
    @Autowired
    public PatientService(ApiProxy api) {
        this.api = api;
    }

    public void create(Patient patient) {
        patient.id = null;
        api.add(patient);
    }

    public List<Patient> readAll() {
        // TODO
        return Collections.emptyList();
    }

    public Optional<Patient> read(Long id) {
        // TODO
        return Optional.empty();
    }

    public void update(Patient patient) {
        // TODO
    }

    public void delete(Integer id) {
        // TODO
    }

}
