package com.mediscreen.abernathy.patient;

import com.mediscreen.abernathy.patient.domain.Patient;
import com.mediscreen.abernathy.patient.dto.PatientDTO;
import com.mediscreen.abernathy.patient.repository.PatientRepository;
import com.mediscreen.abernathy.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        service.create(new PatientDTO());
        verify(repository).save(any());
    }

    @Test
    void when_add_with_id_then_ignore_id() {
        var dto = new PatientDTO();
        dto.id = 1L;
        service.create(dto);
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

    @Test
    void when_update_with_id_then_success() {
        var dto = new PatientDTO();
        dto.id = 1L;
        when(repository.existsById(1L)).thenReturn(true);
        service.update(dto);
        verify(repository).save(any());
    }

    @Test
    void when_update_with_no_id_then_fail() {
        service.update(new PatientDTO());
        verify(repository, times(0)).save(any());
    }

    @Test
    void when_update_with_wrong_id_then_fail() {
        var dto = new PatientDTO();
        dto.id = 0L;
        when(repository.existsById(0L)).thenReturn(false);
        service.update(dto);
        verify(repository, times(0)).save(any());
    }

    @Test
    void when_delete_then_success() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Patient()));
        service.delete(1L);
        verify(repository).delete(any());
    }
}
