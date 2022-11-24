package com.mediscreen.abernathy.web.controller;

import com.mediscreen.abernathy.web.dto.Note;
import com.mediscreen.abernathy.web.service.NotesService;
import com.mediscreen.abernathy.web.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {

    private final PatientService patientService;
    private final NotesService notesService;
    @Autowired
    public HistoryController(PatientService patientService, NotesService notesService) {
        this.patientService = patientService;
        this.notesService = notesService;
    }

    @GetMapping("/history")
    public String baseDir() {
        return "redirect:/history/list";
    }

    @GetMapping("/history/list")
    public String baseDir(Long patId, Model model, Note newNote) {
        model.addAttribute("patients", patientService.readAll());
        model.addAttribute("selectedId", patId);
        model.addAttribute("notes", notesService.readByPatientId(patId));
        return "history/list";
    }

}
