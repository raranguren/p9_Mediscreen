package com.mediscreen.abernathy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService service;
    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient add(@Valid @RequestBody PatientDTO patientDTO) {
        return service.add(patientDTO);
    }

    // Allows adding values from CURL/Postman without validation
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Patient AddUrlEncoded(@RequestBody MultiValueMap<String, String> map) {
        return add(PatientDTO.instanceFrom(map));
    }

}