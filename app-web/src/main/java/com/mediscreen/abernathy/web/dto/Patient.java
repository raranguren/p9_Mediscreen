package com.mediscreen.abernathy.web.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Validated
@Getter
@Setter
public class Patient {

    @Positive(message = "Plus grand que zéro")
    public Long id;

    @NotBlank(message = "Requis")
    @Size(max = 20, message = "Taille maximale de {max} caractères")
    public String family;

    @NotBlank(message = "Requis")
    @Size(max = 20, message = "Taille maximale de {max} caractères")
    public String given;

    @NotBlank(message = "Requis")
    public String dob;

    @NotBlank(message = "Requis")
    public String sex;

    @NotBlank(message = "Requis")
    @Size(max = 40, message = "Taille maximale de {max} caractères")
    public String address;

    @NotBlank(message = "Requis")
    @Pattern(regexp = "(\\d\\d\\d-?\\d\\d\\d-?\\d\\d\\d\\d)?", message = "Doit comporter 10 chiffres (123-456-7890)")
    public String phone;

}
