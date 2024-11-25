package com.truonglq.licensingservice.service.client;

import com.truonglq.licensingservice.model.Organization;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Organization getOrganization(String organizationId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://localhost:8081/v1/organization/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);
        return restExchange.getBody();
    }
}