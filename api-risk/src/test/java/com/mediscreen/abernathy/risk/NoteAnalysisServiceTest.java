package com.mediscreen.abernathy.risk;

import com.mediscreen.abernathy.risk.proxy.NotesProxy;
import com.mediscreen.abernathy.risk.service.NoteAnalysisService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.mediscreen.abernathy.risk.service.NoteAnalysisService.DIABETES_TRIGGERS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteAnalysisServiceTest {

    @InjectMocks
    NoteAnalysisService service;

    @Mock
    NotesProxy notesProxy;

    @Test
    void when_all_triggers_in_different_notes_and_positions_then_count_all() {
        var notes = List.of("note 1 AAA", "BBB note 2", "note CCC 3");
        when(notesProxy.readNoteStringsByPatientId(anyLong())).thenReturn(notes);
        int result = service.getTriggerCount(1L, List.of("AAA", "BBB", "CCC"));
        assertEquals(3, result);
    }

    @Test
    void when_upcase_then_count_lower_case() {
        var notes = List.of("aaa", "Bbb", "CCC");
        when(notesProxy.readNoteStringsByPatientId(anyLong())).thenReturn(notes);
        int result = service.getTriggerCount(1L, List.of("AAA", "bBB", "ccc"));
        assertEquals(3, result);
    }


    @Test
    void when_all_triggers_in_the_same_note_then_count_all() {
        String note = "this AAA contains BBB all CCC triggers";
        when(notesProxy.readNoteStringsByPatientId(anyLong())).thenReturn(List.of(note));
        int result = service.getTriggerCount(1L, List.of("aaa","bbb","ccc"));
        assertEquals(3, result);
    }

    @Test
    void when_no_match_then_count_zero() {
        int result = service.getTriggerCount(2L, DIABETES_TRIGGERS);
        assertEquals(0, result);
    }

    @Test
    void when_triggers_repeatedly_then_count_once() {
        var notes = List.of("AAABBBCCC", "AAABBB", "CCC");
        when(notesProxy.readNoteStringsByPatientId(anyLong())).thenReturn(notes);
        int result = service.getTriggerCount(1L, List.of("aaa","bbb","ccc"));
        assertEquals(3, result);
    }

}
