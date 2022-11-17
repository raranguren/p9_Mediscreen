package com.mediscreen.abernathy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebService {

    @Autowired
    ApiProxy api;

    public String hello() {
        return api.hello().orElse("The API didn't respond");
    }
}
