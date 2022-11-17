package com.mediscreen.abernathy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("hello")
    public String hello() {
        System.out.println("API called");
        return "Hello from the API!";
    }

}
