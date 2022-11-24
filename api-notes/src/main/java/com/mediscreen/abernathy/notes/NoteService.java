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
        return repository
                .findAllByPatId(id)
                .stream()
                .map(NoteDTO::new)
                .collect(Collectors.toList());
    }

    public void create(NoteDTO dto) {
        dto.id = null;
        repository.save(dto.toEntity());
    }

    public void update(NoteDTO dto) throws IdNotFoundException {
        var newNote = dto.toEntity();
        var oldNote = repository.findById(newNote.id)
                .orElseThrow(IdNotFoundException::new);
        newNote.patId = oldNote.patId;
        repository.save(newNote);
    }

    public void delete(String id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

    public NoteDTO findByNoteId(String id) throws IdNotFoundException {
        var note = repository.findById(id)
                .orElseThrow(IdNotFoundException::new);
        return new NoteDTO(note);
    }
}
