package com.mediscreen.abernathy.web.controller;

import com.mediscreen.abernathy.web.dto.Note;
import com.mediscreen.abernathy.web.service.NotesService;
import com.mediscreen.abernathy.web.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String list(Long patId, Model model) {
        model.addAttribute("patients", patientService.readAll());
        model.addAttribute("selectedId", patId);
        model.addAttribute("notes", notesService.readByPatientId(patId));
        return "history/list";
    }

    @PostMapping("/history/add")
    public String add(Note note) {
        notesService.add(note);
        return "redirect:/history/list?patId=" + note.patId;
    }

}
