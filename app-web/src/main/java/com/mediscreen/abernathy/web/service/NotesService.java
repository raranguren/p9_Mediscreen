package com.mediscreen.abernathy.web.service;

import com.mediscreen.abernathy.web.dto.Note;
import com.mediscreen.abernathy.web.proxy.NotesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    private final NotesProxy api;
    @Autowired
    public NotesService(NotesProxy api) {
        this.api = api;
    }

    public List<Note> readByPatientId(Long patId) {
        return api.readByPatientId(patId);
    }

    public void add(Note note) {
        api.add(note);
    }

    public void delete(String noteId) {
        api.delete(noteId);
    }

    public void update(Note note) {
        api.update(note);
    }

    public Optional<Note> readByNoteId(String noteId) {
        return api.readByNoteId(noteId);
    }
}
