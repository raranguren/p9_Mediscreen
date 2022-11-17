package com.mediscreen.abernathy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    public static final String HELLO_URI = "/hello";

    @GetMapping(HELLO_URI)
    public String hello() {
        System.out.println("API called");
        return "Hello from the API!";
    }

}
