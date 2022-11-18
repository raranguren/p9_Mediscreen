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

}
