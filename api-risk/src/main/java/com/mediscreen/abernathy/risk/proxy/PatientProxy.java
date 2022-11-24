package com.mediscreen.abernathy.risk.proxy;


import com.mediscreen.abernathy.risk.domain.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.Optional;

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

    public Optional<Patient> read(Long patId) {
        return client.get().uri("patient/read/" + patId)
                .retrieve().bodyToMono(Patient.class).blockOptional();
    }

}
