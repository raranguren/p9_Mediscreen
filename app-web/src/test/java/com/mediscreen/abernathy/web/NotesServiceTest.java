package com.mediscreen.abernathy.web;

import com.mediscreen.abernathy.web.dto.Note;
import com.mediscreen.abernathy.web.proxy.NotesProxy;
import com.mediscreen.abernathy.web.service.NotesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotesServiceTest {

    @InjectMocks
    NotesService service;

    @Mock
    NotesProxy proxy;

    @Test
    void when_list_then_success() {
        var note = new Note("xxx", 1L, "note");
        when(proxy.readByPatientId(1L)).thenReturn(List.of(note));
        var result = service.readByPatientId(1L);
        assertEquals("note", result.get(0).e);
    }

    @Test
    void when_add_then_success() {
        var note = new Note("xxx", 1L, "note");
        service.add(note);
        verify(proxy).add(note);
    }

    @Test
    void when_delete_then_success() {
        service.delete("xxx");
        verify(proxy).delete("xxx");
    }

    @Test
    void when_update_then_success() {
        var note = new Note("xxx", 1L, "note");
        service.update(note);
        verify(proxy).update(any());
    }

    @Test
    void when_find_one_note_then_success() {
        var note = new Note("xxx", 1L, "note");
        when(proxy.readByNoteId("xxx")).thenReturn(Optional.of(note));
        var result = service.readByNoteId("xxx");
        assert(result.isPresent());
        assertEquals("note", result.get().e);
    }

}
