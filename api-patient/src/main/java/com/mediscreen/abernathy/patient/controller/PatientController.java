package com.mediscreen.abernathy.patient.controller;

import com.mediscreen.abernathy.patient.service.PatientService;
import com.mediscreen.abernathy.patient.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService service;
    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("list")
    public List<PatientDTO> list() {
        return service.readAll();
    }

    @GetMapping("read/{id}")
    public Optional<PatientDTO> read(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO add(@Valid @RequestBody PatientDTO patientDTO) {
        return service.create(patientDTO);
    }

    @PostMapping("update")
    public PatientDTO update(@Valid @RequestBody PatientDTO patientDTO) {
        return service.update(patientDTO);
    }

    @GetMapping("delete/{id}")
    public PatientDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }

    // Handlers for MediaType x-www-form-urlencoded

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO AddUrlEncoded(@Valid PatientDTO patientDTO) {
        return add(patientDTO);
    }

    @PostMapping(value = "update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public PatientDTO updateUrlEncoded(@Valid PatientDTO patientDTO) {
        return update(patientDTO);
    }

}
