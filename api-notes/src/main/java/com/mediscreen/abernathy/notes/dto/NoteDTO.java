package com.mediscreen.abernathy.notes.dto;

import com.mediscreen.abernathy.notes.domain.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    public String id;
    public Long patId;
    public String e;

    public NoteDTO(Note fromEntity) {
        this(fromEntity.id, fromEntity.patId, fromEntity.text);
    }

    public Note toEntity() {
        String fixedText = e==null ? null : e.replace("\\n", "\n");
        return new Note(id, patId, fixedText);
    }

}
