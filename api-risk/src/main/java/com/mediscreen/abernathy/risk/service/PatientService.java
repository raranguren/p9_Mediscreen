package com.mediscreen.abernathy.risk.service;

import com.mediscreen.abernathy.risk.domain.Patient;
import com.mediscreen.abernathy.risk.exception.PatientNotFoundException;
import com.mediscreen.abernathy.risk.proxy.NotesProxy;
import com.mediscreen.abernathy.risk.proxy.PatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientProxy patientProxy;
    private final NotesProxy notesProxy;
    @Autowired
    public PatientService(PatientProxy patientProxy, NotesProxy notesProxy) {
        this.patientProxy = patientProxy;
        this.notesProxy = notesProxy;
    }

    public Patient readById(Long patId) {
        var patient = this.patientProxy.findById(patId)
                .orElseThrow(PatientNotFoundException::new);
        patient.notes = notesProxy.readNotesByPatientId(patId);
        return patient;
    }

    public Patient readByFamilyName(String family) {
        var patient = this.patientProxy.findByFamilyName(family)
                .orElseThrow(PatientNotFoundException::new);
        patient.notes = notesProxy.readNotesByPatientId(patient.id);
        return patient;
    }



}
