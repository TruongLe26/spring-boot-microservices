package com.truonglq.licensingservice.controller;

import com.truonglq.licensingservice.config.ServiceConfig;
import com.truonglq.licensingservice.model.ConfigBean;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestConfigController {

    private final ServiceConfig config;
    private final ConfigBean configBean;

//    @Value("${}")
//    private String anExistingProperty;

    @GetMapping("/test")
    public String getNewestProperty() {
        return config.getProperty();
    }

    @GetMapping("/updated")
    public String getUpdatedProperty() {
        return configBean.getNewProperty();
    }

}
