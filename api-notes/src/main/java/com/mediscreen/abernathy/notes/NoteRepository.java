package com.mediscreen.abernathy.notes;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {

}
