package com.mediscreen.abernathy.risk.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PatientProfile {

    public String given;
    public String family;
    public Integer age;
    public Sex sex;
    public Integer diabetesTriggerCount;

}
