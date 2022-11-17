package com.mediscreen.abernathy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class ApiProxy {

    @Autowired
    private WebClient client;

    public Optional<String> hello() {
        return client.get().uri("/hello")
                .retrieve().bodyToMono(String.class).blockOptional();
    }
}
