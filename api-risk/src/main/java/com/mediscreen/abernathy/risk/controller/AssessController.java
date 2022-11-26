package com.mediscreen.abernathy.risk.controller;

import com.mediscreen.abernathy.risk.dto.DiabetesReportDTO;
import com.mediscreen.abernathy.risk.exception.PatientNotFoundException;
import com.mediscreen.abernathy.risk.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssessController {

    private final ReportService service;
    @Autowired
    public AssessController(ReportService service) {
        this.service = service;
    }

    // JSON handlers

    @GetMapping("assess/id/{patId}")
    public DiabetesReportDTO assessById(@PathVariable Long patId) {
        return service.getReport(patId);
    }

    // HTML FORM handlers

    @PostMapping(path = "assess/id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String assesByIdUrlEncoded(Long patId) {
        return service.getReport(patId).toString();
    }

    @PostMapping(path = "assess/familyName", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String assessByFamilyNameUrlEncoded(String familyName) {
        return service.getReport(familyName).toString();
    }

    // Error 404 when patient not found

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFoundHandler() {}

}
