package com.mediscreen.abernathy.notes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @InjectMocks
    NoteService service;

    @Mock
    NoteRepository repository;

    @Test
    void when_read_all_then_success() {
        when(repository.findAllByPatId(anyLong())).thenReturn(Collections.emptyList());
        var result = service.findAllByPatientId(1L);
        assertNotNull(result);
        verify(repository).findAllByPatId(anyLong());
    }

}
