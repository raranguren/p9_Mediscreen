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

    @PostConstruct
    public void buildWebClient() {
        this.client = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }

    public Optional<Patient> add(Patient patient) {
        return client.post().uri("/patient/add").bodyValue(patient)
                .retrieve().bodyToMono(Patient.class)
                .onErrorComplete().blockOptional();
    }

    public List<Patient> readAll() {
        return client.get().uri("patient/list")
                .retrieve().bodyToFlux(Patient.class)
                .onErrorComplete()
                .collectList().block();
    }

    public Patient update(Patient patient) {
        return client.post().uri("/patient/update").bodyValue(patient)
                .retrieve().bodyToMono(Patient.class)
                .onErrorComplete().block();
    }

    public Optional<Patient> read(Long id) {
        return client.get().uri("patient/read/" + id)
                .retrieve().bodyToMono(Patient.class)
                .onErrorComplete().blockOptional();
    }

    public Patient delete(Long id) {
        return client.get().uri("/patient/delete/" + id)
                .retrieve().bodyToMono(Patient.class)
                .onErrorComplete().block();
    }
}
