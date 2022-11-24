package com.mediscreen.abernathy.web.service;

import com.mediscreen.abernathy.web.dto.Note;
import com.mediscreen.abernathy.web.proxy.NotesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
