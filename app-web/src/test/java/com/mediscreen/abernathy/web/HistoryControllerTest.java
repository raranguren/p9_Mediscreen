package com.mediscreen.abernathy.web;

import com.mediscreen.abernathy.web.controller.HistoryController;
import com.mediscreen.abernathy.web.service.NotesService;
import com.mediscreen.abernathy.web.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HistoryController.class)
public class HistoryControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PatientService patientService;
    @MockBean
    NotesService notesService;

    @Test
    public void list_compiles() throws Exception {
        mvc.perform(get("/history/list"))
                .andExpect(status().is2xxSuccessful());
    }

}
