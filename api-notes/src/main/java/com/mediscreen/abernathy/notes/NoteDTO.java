package com.mediscreen.abernathy.notes;

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
        return new Note(id, patId, e.replace("\\n", "\n"));
    }

}
