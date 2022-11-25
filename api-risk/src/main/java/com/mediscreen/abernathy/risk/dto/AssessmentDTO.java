package com.mediscreen.abernathy.risk.dto;

import com.mediscreen.abernathy.risk.RiskLevel;

public class AssessmentDTO {

    public String given;
    public String family;
    public Integer age;
    public RiskLevel risk;

    @Override
    public String toString() {
        return "Patient: " + given + " " + family
                + " (age " + age + ") diabetes assessment is: " + risk;
    }
}
