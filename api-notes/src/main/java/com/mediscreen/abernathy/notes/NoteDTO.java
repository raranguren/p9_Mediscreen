package com.mediscreen.abernathy.notes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    public String id;

    @NotNull
    public Long patId;

    @NotEmpty
    public String note;


    public NoteDTO(Note document) {
        this(document.id, document.patId, document.note);
    }

    public Note toDocument() {
        return new Note(id, patId, note);
    }

}
