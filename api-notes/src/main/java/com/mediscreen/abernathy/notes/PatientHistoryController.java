package com.mediscreen.abernathy.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/patHistory")
public class PatientHistoryController {

    private final NoteService service;
    @Autowired
    public PatientHistoryController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/patient/{id}")
    public List<NoteDTO> notesByPatientId(@PathVariable Long id) {
        return service.findAllByPatientId(id);
    }

}
