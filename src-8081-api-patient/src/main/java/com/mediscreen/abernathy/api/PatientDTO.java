package com.mediscreen.abernathy.api;

import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Validated
public class PatientDTO {

    @Positive
    public Long id;

    @NotBlank
    @Size(max = 20)
    public String family;

    @NotBlank
    @Size(max = 20)
    public String given;

    @NotNull
    @Past
    public LocalDate dob;

    @NotBlank
    @Pattern(regexp = "[MF]")
    public String sex;

    @NotBlank
    @Size(max = 80)
    public String address;

    @NotBlank
    @Pattern(regexp = "\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")
    public String phone;

    public static PatientDTO instanceFrom(MultiValueMap<String, String> map) {
        var dto = new PatientDTO();
        var id = map.getFirst("id");
        if (id != null) dto.id = Long.parseLong(id);
        dto.family = map.getFirst("family");
        dto.given = map.getFirst("given");
        var dob = map.getFirst("dob");
        if (dob != null) dto.dob = LocalDate.parse(dob);
        dto.sex = map.getFirst("sex");
        dto.address = map.getFirst("address");
        dto.phone = map.getFirst("phone");
        return dto;
    }

    public Patient toEntity() {
        var entity = new Patient();
        entity.id = id;
        entity.family = family;
        entity.given = given;
        entity.dob = dob;
        entity.sex = sex;
        entity.address = address;
        entity.phone = phone;
        return entity;
    }
}
