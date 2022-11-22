package com.mediscreen.abernathy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO create(PatientDTO patientDTO) {
        System.out.println(patientDTO.family);
        return service.add(patientDTO);
    }

    @PostMapping("update")
    @ResponseStatus(HttpStatus.OK)
    public PatientDTO update(@Valid @RequestBody PatientDTO patientDTO) {
        return service.update(patientDTO);
    }

    @GetMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }

}
