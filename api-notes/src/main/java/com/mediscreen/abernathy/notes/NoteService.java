package com.mediscreen.abernathy.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository repository;
    @Autowired
    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<NoteDTO> findAllByPatientId(Long id) {
        return repository.findAllByPatId(id)
                .stream().map(NoteDTO::new)
                .collect(Collectors.toList());
    }

    public void create(NoteDTO dto) {
        dto.id = null;
        repository.save(dto.toEntity());
    }
}
