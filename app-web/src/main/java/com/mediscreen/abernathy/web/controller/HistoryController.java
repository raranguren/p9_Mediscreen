package com.mediscreen.abernathy.web.controller;

import com.mediscreen.abernathy.web.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {

    private final PatientService patientService;
    @Autowired
    public HistoryController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/history")
    public String baseDir() {
        return "redirect:/history/list";
    }

    @GetMapping("/history/list")
    public String baseDir(Long patId, Model model) {
        model.addAttribute("patients", patientService.readAll());
        model.addAttribute("selectedId", patId);
        return "history/list";
    }

}
