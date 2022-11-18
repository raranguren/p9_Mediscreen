package com.mediscreen.abernathy.web.proxy;

import com.mediscreen.abernathy.web.dto.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class ApiProxy {

    private final WebClient client;
    @Autowired
    public ApiProxy(WebClient client) {
        this.client = client;
    }

    public Optional<Patient> add(Patient patient) {
        return client.post().uri("/patient/add").bodyValue(patient)
                .retrieve().bodyToMono(Patient.class).blockOptional();
    }
}
