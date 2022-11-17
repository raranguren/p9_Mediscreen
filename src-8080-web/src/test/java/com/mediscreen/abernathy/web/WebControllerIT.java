package com.mediscreen.abernathy.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerIT {

    @Autowired
    MockMvc mvc;

    @MockBean
    WebController webController;

    @Test
    public void hello() throws Exception {
        mvc.perform(get(WebController.ENDPOINT_HELLO))
                .andExpect(status().is2xxSuccessful());
    }

}
