package com.mediscreen.abernathy.web.service;

import com.mediscreen.abernathy.web.dto.RiskReport;
import com.mediscreen.abernathy.web.proxy.RiskProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiskService {

    private final RiskProxy api;
    @Autowired
    public RiskService(RiskProxy api) {
        this.api = api;
    }

    public RiskReport getRiskReport(Long patId) {
        if (patId == null) return null;
        return api.readByPatientId(patId);
    }

}
