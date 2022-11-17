package com.mediscreen.abernathy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    public static final String ENDPOINT_HELLO = "/hello";
    @Autowired
    WebService service;

    @GetMapping(ENDPOINT_HELLO)
    public ModelAndView test(ModelMap model) {
        model.addAttribute("hello", service.hello());
        return new ModelAndView("hello", model);
    }

}
