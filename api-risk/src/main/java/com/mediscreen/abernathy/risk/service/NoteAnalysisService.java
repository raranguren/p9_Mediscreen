package com.mediscreen.abernathy.risk.service;

import com.mediscreen.abernathy.risk.proxy.NotesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static io.netty.util.AsciiString.containsIgnoreCase;

@Service
public class NoteAnalysisService {

    private final NotesProxy notesProxy;
    @Autowired
    public NoteAnalysisService(NotesProxy notesProxy) {
        this.notesProxy = notesProxy;
    }

    public int getTriggerCount(Long patId, List<String> triggers) {
        var comments = notesProxy.readNoteStringsByPatientId(patId);
        AtomicInteger count = new AtomicInteger(0);
        triggers.parallelStream().forEach(trigger -> {
            for (var comment : comments) {
                if (containsIgnoreCase(comment, trigger)) {
                    count.getAndIncrement();
                    break;
                }
            }
        });
        return count.get();
    }
}
