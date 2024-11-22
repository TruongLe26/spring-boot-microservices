package com.truonglq.licensingservice.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@Getter
public class ConfigBean {

    @Value("${example.property}")
    private String newProperty;

}
