package com.mediscreen.abernathy.risk.dto;

import com.mediscreen.abernathy.risk.domain.RiskLevel;

public class AssessmentDTO {

    public String given;
    public String family;
    public Integer age;
    public RiskLevel diabetesRisk;

    @Override
    public String toString() {
        return "Patient: " + given + " " + family
                + " (age " + age + ") diabetes assessment is: " + diabetesRisk;
    }
}
