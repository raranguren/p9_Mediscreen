package com.mediscreen.abernathy.web.controller;

import com.mediscreen.abernathy.web.service.PatientService;
import com.mediscreen.abernathy.web.dto.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PatientController {

    private final PatientService service;
    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping({"/", "/patient"})
    public String baseDir() {
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/list")
    public String list(Model model) {
        model.addAttribute("list", service.readAll());
        return "patient/list";
    }

    @GetMapping("/patient/add")
    public String addForm(Patient patient) {
        return "patient/add";
    }

    @PostMapping("/patient/add")
    public String add(@Valid Patient patient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "patient/add";
        }
        service.create(patient);
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        var existingRecord = service.read(id);
        if (existingRecord.isEmpty()) {
            return "redirect:/patient/list";
        }
        model.addAttribute("patient", existingRecord.get());
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid Patient patient,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "patient/update";
        }
        patient.id = id;
        service.update(patient);
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/patient/list";
    }

}
