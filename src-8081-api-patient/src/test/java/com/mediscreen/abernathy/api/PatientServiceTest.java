package com.mediscreen.abernathy.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void when_readAll_then_success() {
        service.readAll();
        verify(repository).findAll();
    }

    @Test
    void when_read_then_phone_number_has_hypens() {
        var dto = new PatientDTO();
        dto.phone= "123-456-7890";
        when(repository.findAll()).thenReturn(List.of(dto.toEntity()));
        var result = service.readAll();
        assertEquals(dto.phone, result.get(0).phone);
    }
}
