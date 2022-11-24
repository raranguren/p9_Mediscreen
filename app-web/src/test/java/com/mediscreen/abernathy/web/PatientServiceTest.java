package com.mediscreen.abernathy.web;

import com.mediscreen.abernathy.web.dto.Patient;
import com.mediscreen.abernathy.web.proxy.PatientProxy;
import com.mediscreen.abernathy.web.service.PatientService;
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
public class PatientServiceTest {

    @InjectMocks
    PatientService service;

    @Mock
    PatientProxy api;

    @Test
    void when_list_then_success() {
        when(api.readAll()).thenReturn(List.of(new Patient()));
        var result = service.readAll();
        assertEquals(1, result.size());
    }

    @Test
    void when_add_then_success() {
        when(api.add(any())).thenReturn(Optional.of(new Patient()));
        service.create(new Patient());
        verify(api).add(any(Patient.class));
    }

    @Test
    void when_update_then_success() {
        when(api.update(any())).thenReturn(new Patient());
        service.update(new Patient());
        verify(api).update(any(Patient.class));
    }

}
