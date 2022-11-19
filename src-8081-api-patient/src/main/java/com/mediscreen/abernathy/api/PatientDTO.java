package com.mediscreen.abernathy.api;

import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @NotBlank
    @Pattern(regexp = "\\d\\d\\d\\d[-/]\\d\\d[-/]\\d\\d")
    public String dob;

    @NotBlank
    @Pattern(regexp = "[MF]")
    public String sex;

    @NotBlank
    @Size(max = 80)
    public String address;

    @NotBlank
    @Pattern(regexp = "\\d\\d\\d-?\\d\\d\\d-?\\d\\d\\d\\d")
    public String phone;

    public PatientDTO() {}
    public PatientDTO(MultiValueMap<String, String> map) {
        var id = map.getFirst("id");
        if (id != null) this.id = Long.parseLong(id);
        this.family = map.getFirst("family");
        this.given = map.getFirst("given");
        this.dob = map.getFirst("dob");
        this.sex = map.getFirst("sex");
        this.address = map.getFirst("address");
        this.phone = map.getFirst("phone");
    }
    public PatientDTO(Patient entity) {
        this.id = entity.id;
        this.family = entity.family;
        this.given = entity.given;
        this.dob = entity.dob.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.sex = entity.sex;
        this.address = entity.address;
        this.phone = entity.phone;
    }

    public Patient toEntity() {
        var entity = new Patient();
        entity.id = id;
        entity.family = family;
        entity.given = given;
        if (dob != null) entity.dob = LocalDate.parse(dob);
        entity.sex = sex;
        entity.address = address;
        entity.phone = phone;
        return entity;
    }

}
