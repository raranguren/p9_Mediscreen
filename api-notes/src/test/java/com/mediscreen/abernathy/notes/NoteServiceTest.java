package com.mediscreen.abernathy.notes;

import com.mediscreen.abernathy.notes.domain.Note;
import com.mediscreen.abernathy.notes.dto.NoteDTO;
import com.mediscreen.abernathy.notes.exception.IdNotFoundException;
import com.mediscreen.abernathy.notes.repository.NoteRepository;
import com.mediscreen.abernathy.notes.service.NoteService;
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

    @Test
    void when_delete_then_success() {
        when(repository.existsById("abcd")).thenReturn(true);
        service.delete("abcd");
        verify(repository).deleteById("abcd");
    }

    @Test
    void when_read_by_id_then_success() throws Exception {
        when(repository.findById("abab")).thenReturn(Optional.of(new Note("abab", 1L, "testtext")));
        var result = service.findByNoteId("abab");
        assertEquals("testtext", result.e);
    }

}
