package com.mediscreen.abernathy.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Validated
@Getter
@Setter
public class PatientDTO {

    @Positive(message = "Must be greater than zero")
    public Long id;

    @NotBlank(message = "Required")
    @Size(max = 20, message = "Maximum size {max} characters")
    public String family;

    @NotBlank(message = "Required")
    @Size(max = 20, message = "Maximum size {max} characters")
    public String given;

    @NotBlank(message = "Required")
    @Pattern(regexp = "\\d\\d\\d\\d[-/]\\d\\d[-/]\\d\\d", message = "Date format is YYYY-MM-DD")
    public String dob;

    @NotBlank(message = "Required")
    @Pattern(regexp = "[MF]", message = "M or F")
    public String sex;

    @NotBlank(message = "Required")
    @Size(max = 80, message = "Maximum size {max} characters")
    public String address;

    @NotBlank(message = "Required")
    @Pattern(regexp = "\\d\\d\\d-?\\d\\d\\d-?\\d\\d\\d\\d", message = "Phone must have 10 digits (123-456-7890)")
    public String phone;

    public static PatientDTO instanceFrom(MultiValueMap<String, String> map) {
        var dto = new PatientDTO();
        var id = map.getFirst("id");
        if (id != null) dto.id = Long.parseLong(id);
        dto.family = map.getFirst("family");
        dto.given = map.getFirst("given");
        dto.dob = map.getFirst("dob");
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
        if (dob != null) entity.dob = LocalDate.parse(dob);
        entity.sex = sex;
        entity.address = address;
        entity.phone = phone;
        return entity;
    }

}
