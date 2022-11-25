package com.mediscreen.abernathy.web;

import com.mediscreen.abernathy.web.proxy.RiskProxy;
import com.mediscreen.abernathy.web.service.RiskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RiskServiceTest {

    @InjectMocks
    RiskService service;

    @Mock
    RiskProxy proxy;

    @Test
    void uses_proxy() {
        service.getRiskReport(1L);
        verify(proxy).readByPatientId(1L);
    }

}
