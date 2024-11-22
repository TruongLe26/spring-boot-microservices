package com.truonglq.licensingservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "new")
@Getter
@Setter
public class ServiceConfig {

    private String property;

}
