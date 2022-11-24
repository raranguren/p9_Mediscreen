package com.mediscreen.abernathy.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    public String id;

    public Long patId;

    @NotBlank(message = "Requis")
    public String e;

}
