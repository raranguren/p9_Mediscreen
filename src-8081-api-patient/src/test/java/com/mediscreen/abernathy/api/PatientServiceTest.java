package com.mediscreen.abernathy.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    PatientService service;

    @Mock
    PatientRepository repository;

    @Captor
    ArgumentCaptor<Patient> captor;

    @Test
    void when_add_then_success() {
        service.add(new PatientDTO());
        verify(repository).save(any());
    }

    @Test
    void when_add_with_id_then_ignore_id() {
        var dto = new PatientDTO();
        dto.id = 1L;
        service.add(dto);
        verify(repository).save(captor.capture());
        var patient = captor.getValue();
        assertNull(patient.id);
    }

}
