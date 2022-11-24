package com.mediscreen.abernathy.web.proxy;

import com.mediscreen.abernathy.web.dto.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class PatientProxy {

    @Value("${api-patient-url}")
    private String API_URL;
    private WebClient client;

    private final Map<Long, Patient> cache = new HashMap<>();

    @PostConstruct
    public void buildWebClient() {
        this.client = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
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
