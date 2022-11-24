package com.mediscreen.abernathy.risk;

import com.mediscreen.abernathy.risk.domain.Assessment;
import com.mediscreen.abernathy.risk.exception.PatientNotFoundException;
import com.mediscreen.abernathy.risk.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssessController {

    private final AssessmentService service;
    @Autowired
    public AssessController(AssessmentService service) {
        this.service = service;
    }

    @PostMapping(path = "assess/id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String assessmentByIdUrlEncoded(Long patId) {
        return service.getReport(patId).toString();
    }

    @PostMapping("assess/id/{patId}")
    public Assessment assessment(@PathVariable Long patId) {
        return service.getReport(patId);
    }

    @PostMapping(path = "assess/familyName", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String assessmentByIdUrlEncoded(String family) {
        return service.getReport(family).toString();
    }

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFoundHandler() {}

}
