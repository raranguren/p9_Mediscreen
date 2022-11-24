package com.mediscreen.abernathy.web;

import com.mediscreen.abernathy.web.controller.PatientController;
import com.mediscreen.abernathy.web.dto.Patient;
import com.mediscreen.abernathy.web.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PatientService service;

    @Test
    public void list_compiles() throws Exception {
        mvc.perform(get("/patient/list"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void add_form_compiles() throws Exception {
        mvc.perform(get("/patient/add"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void update_form_compiles() throws Exception {
        when(service.read(1L)).thenReturn(Optional.of(new Patient()));
        mvc.perform(get("/patient/update/1"))
                .andExpect(status().is2xxSuccessful());
    }

}
