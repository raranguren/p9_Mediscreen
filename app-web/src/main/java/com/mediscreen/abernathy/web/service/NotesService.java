package com.mediscreen.abernathy.web.service;

import com.mediscreen.abernathy.web.dto.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    public List<Note> readByPatientId(Long patId) {
        // TODO
        return List.of(new Note(null, null, "This is text in 3 lines.\nSecond line\nAnd the third line is longer."));
    }
}
