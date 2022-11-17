package com.mediscreen.abernathy.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
public class WebControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    WebService service;

    @Test
    public void compiles_thymeleaf_for_hello() throws Exception {
        mvc.perform(get(WebController.ENDPOINT_HELLO))
                .andExpect(status().is2xxSuccessful());
    }

}
