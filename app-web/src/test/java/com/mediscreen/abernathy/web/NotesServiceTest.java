package com.mediscreen.abernathy.web;

import com.mediscreen.abernathy.web.service.NotesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotesServiceTest {

    @InjectMocks
    NotesService service;

    //@Mock
    //ApiNotesProxy api;

    @Test
    void when_list_then_success() {
        // TODO
    }

}
