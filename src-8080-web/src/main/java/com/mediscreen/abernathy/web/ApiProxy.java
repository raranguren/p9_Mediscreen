package com.mediscreen.abernathy.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class ApiProxy {

    @Value("${api-url}")
    private String API_URL;
    private WebClient client;

    @PostConstruct
    public void buildClient() {
        this.client = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }

    public Optional<String> hello() {
        System.out.println("URL = " + API_URL);
        return client.get().uri("/hello")
                .retrieve().bodyToMono(String.class).blockOptional();
    }
}
