package com.mediscreen.abernathy.notes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @InjectMocks
    NoteService service;

    @Mock
    NoteRepository repository;

    @Captor
    ArgumentCaptor<Note> noteCaptor;

    @Test
    void when_read_all_then_success() {
        when(repository.findAllByPatId(anyLong())).thenReturn(Collections.emptyList());
        var result = service.findAllByPatientId(1L);
        assertNotNull(result);
        verify(repository).findAllByPatId(anyLong());
    }

    @Test
    void when_add_then_success() {
        service.create(new NoteDTO(null, 1L, "text"));
        verify(repository).save(any(Note.class));
    }

    @Test
    void when_update_then_success() throws Exception {
        var existingNote = new Note("xxxidxxx", 1L, "oldText");
        when(repository.findById("xxxidxxx")).thenReturn(Optional.of(existingNote));
        service.update(new NoteDTO("xxxidxxx", null, "newText"));
        verify(repository).save(noteCaptor.capture());
        var value = noteCaptor.getValue();
        assertEquals("xxxidxxx", value.id);
        assertEquals(1L, value.patId);
        assertEquals("newText", value.text);
    }

    @Test
    void when_update_then_fail() {
        Executable action = ()-> service.update(new NoteDTO());
        assertThrows(IdNotFoundException.class, action);
    }

}
