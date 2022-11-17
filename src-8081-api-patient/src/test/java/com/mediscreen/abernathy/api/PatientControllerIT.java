package com.mediscreen.abernathy.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PatientController.class)
@AutoConfigureMockMvc
public class PatientControllerIT {

    @Autowired
    MockMvc mvc;

    @Test
    public void hello() throws Exception {
        mvc.perform(get(PatientController.HELLO_URI))
                .andExpect(status().is2xxSuccessful());
    }

}
