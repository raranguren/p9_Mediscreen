package com.mediscreen.abernathy.notes.repository;

import com.mediscreen.abernathy.notes.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    @Query("{ 'patId' : ?0 }")
    List<Note> findAllByPatId(Long patientId);

}
