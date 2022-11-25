package com.mediscreen.abernathy.risk.service;

import com.mediscreen.abernathy.risk.proxy.NotesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiabetesService {

    String[] triggers = new String[]{
            "Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur",
            "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps"};

    private final NotesProxy notesProxy;
    @Autowired
    public DiabetesService(NotesProxy notesProxy) {
        this.notesProxy = notesProxy;
    }

    public int getTriggerCount(Long patId) {
        var strings = notesProxy.readNoteStringsByPatientId(patId);
        // TODO count how many triggers in the strings
        return 0;
    }
}
