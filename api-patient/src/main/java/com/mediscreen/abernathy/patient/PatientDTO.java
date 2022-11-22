package com.mediscreen.abernathy.patient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Validated
@Getter
@Setter
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
    @Size(max = 40)
    public String address;

    @NotBlank
    @Pattern(regexp = "\\d\\d\\d-?\\d\\d\\d-?\\d\\d\\d\\d")
    public String phone;

    public PatientDTO() {}
    public PatientDTO(Patient entity) {
        if (entity == null) return;
        this.id = entity.id;
        this.family = entity.family;
        this.given = entity.given;
        this.dob = entity.dob == null ? null : entity.dob.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.sex = entity.sex;
        this.address = entity.address;
        this.phone = addHypens(entity.phone);
    }

    public Patient toEntity() {
        var entity = new Patient();
        entity.id = id;
        entity.family = family;
        entity.given = given;
        if (dob != null) entity.dob = LocalDate.parse(dob);
        entity.sex = sex;
        entity.address = address;
        entity.phone = removeHypens(phone);
        return entity;
    }

    // phone number manipulation
    
    private Long removeHypens(String phoneText) {
        try {
            return Long.parseLong(phoneText.replaceAll("\\D", ""));
        } catch (NumberFormatException | NullPointerException e ) {
            return null;
        }
    }
    
    private String addHypens(Long phoneNumber) {
        if (phoneNumber == null) return null;
        return String.valueOf(phoneNumber)
                .replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
    }

}
