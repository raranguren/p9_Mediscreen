package com.mediscreen.abernathy.web.proxy;

import com.mediscreen.abernathy.web.dto.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class NotesProxy {

    @Value("${api-notes-url}")
    private String API_URL;
    private WebClient client;

    @PostConstruct
    public void buildWebClient() {
        this.client = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }

    public List<Note> readByPatientId(Long patId) {
        if (patId == null) return List.of();
        Note[] array = client.get().uri("patHistory/patient/" + patId)
                .retrieve().bodyToMono(Note[].class)
                .onErrorComplete().block();
        if (array == null) return List.of();
        return List.of(array);
    }

    public void add(Note note) {
        if (note == null) return;
        client.post().uri("patHistory/add").bodyValue(note)
                .retrieve().bodyToMono(String.class)
                .onErrorComplete().block();
    }

    public void delete(String noteId) {
        client.get().uri("patHistory/delete/" + noteId)
                .retrieve().bodyToMono(String.class)
                .onErrorComplete().block();
    }

    public void update(Note note) {
        client.post().uri("patHistory/update").bodyValue(note)
                .retrieve().bodyToMono(String.class)
                .onErrorComplete().block();
    }

    public Optional<Note> readByNoteId(String noteId) {
        return client.get().uri("patHistory/read/" + noteId)
                .retrieve().bodyToMono(Note.class)
                .onErrorComplete().blockOptional();
    }
}
