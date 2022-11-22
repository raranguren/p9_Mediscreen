package com.mediscreen.abernathy.web.proxy;

import com.mediscreen.abernathy.web.dto.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class ApiProxy {

    private final Map<Long, Patient> cache = new HashMap<>();

    private final WebClient client;
    @Autowired
    public ApiProxy(WebClient client) {
        this.client = client;
    }

    public Optional<Patient> add(Patient patient) {
        return client.post().uri("/patient/add").bodyValue(patient)
                .retrieve().bodyToMono(Patient.class).blockOptional();
    }

    public List<Patient> readAll() {
        Patient[] array = client.get().uri("patient/list")
                .retrieve().bodyToMono(Patient[].class).block();
        if (array == null) return List.of();
        for (var patient : array) {
            cache.put(patient.id, patient);
        }
        return List.of(array);
    }

    public Patient update(Patient patient) {
        return client.post().uri("/patient/update").bodyValue(patient)
                .retrieve().bodyToMono(Patient.class).block();
    }

    public Optional<Patient> read(Long id) {
        if (cache.containsKey(id)) readAll();
        return Optional.ofNullable(cache.get(id));
    }

    public Patient delete(Long id) {
        return client.get().uri("/patient/delete/" + id)
                .retrieve().bodyToMono(Patient.class).block();
    }
}
