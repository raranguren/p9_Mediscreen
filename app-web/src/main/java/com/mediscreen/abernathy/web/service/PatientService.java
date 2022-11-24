package com.mediscreen.abernathy.web.service;

import com.mediscreen.abernathy.web.dto.Patient;
import com.mediscreen.abernathy.web.proxy.ApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return api.readAll();
    }

    public Optional<Patient> read(Long id) {
        return api.read(id);
    }

    public void update(Patient patient) {
        api.update(patient);
    }

    public void delete(Long id) {
        api.delete(id);
    }
}
