package com.mediscreen.abernathy.risk.proxy;


import com.mediscreen.abernathy.risk.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
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

    public Optional<PatientDTO> findById(Long patId) {
        return client.get().uri("patient/read/" + patId)
                .retrieve().bodyToMono(PatientDTO.class)
                .onErrorComplete().blockOptional();
    }

    public Optional<PatientDTO> findByFamilyName(String familyName) {
        if (familyName == null) return Optional.empty();
        // reading all the patients because a search by surname is not implemented in the API
        var firstMatch = client.get().uri("patient/list")
                .retrieve().bodyToFlux(PatientDTO.class)
                .onErrorComplete()
                .filter(patient -> familyName.equals(patient.family))
                .blockFirst();
        return Optional.ofNullable(firstMatch);
    }
}
