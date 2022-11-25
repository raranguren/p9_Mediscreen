package com.mediscreen.abernathy.web.proxy;

import com.mediscreen.abernathy.web.dto.RiskReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class RiskProxy {

    @Value("${api-risk-url}")
    private String API_URL;
    private WebClient client;

    @PostConstruct
    public void buildWebClient() {
        this.client = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }

    public RiskReport readByPatientId(Long patId) {
        return client.get().uri("/assess/id/" + patId)
                .retrieve().bodyToMono(RiskReport.class)
                .onErrorComplete()
                .block();
    }

}
