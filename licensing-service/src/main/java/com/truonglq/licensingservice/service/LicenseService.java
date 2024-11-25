package com.truonglq.licensingservice.service;

import com.truonglq.licensingservice.config.ServiceConfig;
import com.truonglq.licensingservice.model.License;
import com.truonglq.licensingservice.model.Organization;
import com.truonglq.licensingservice.repository.LicenseRepository;
import com.truonglq.licensingservice.service.client.OrganizationDiscoveryClient;
import com.truonglq.licensingservice.service.client.OrganizationFeignClient;
import com.truonglq.licensingservice.service.client.OrganizationRestTemplateClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseService {

    MessageSource messages;
    LicenseRepository licenseRepository;
    ServiceConfig serviceConfig;
    OrganizationFeignClient organizationFeignClient;
    OrganizationRestTemplateClient organizationRestTemplateClient;
    OrganizationDiscoveryClient organizationDiscoveryClient;

    public License getLicense(String licenseId, String organizationId, String clientType) {
        Optional<License> optionalLicense = licenseRepository.findByLicenseIdAndOrganizationId(licenseId, organizationId);
        if (optionalLicense.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(
                            messages.getMessage("license.search.error.message", null, null),
                            licenseId, organizationId
                    )
            );
        }

        License license = optionalLicense.get();
        Organization organization = retrieveOrganizationInfo(organizationId, clientType);
        if (null != organization) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }

        return license.withComment(serviceConfig.getProperty());
    }

    public License createLicense(License license){
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);

        return license.withComment(serviceConfig.getProperty());
    }

    public License updateLicense(License license){
        licenseRepository.save(license);

        return license.withComment(serviceConfig.getProperty());
    }

    public String deleteLicense(String licenseId){
        String responseMessage = null;
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        responseMessage = String.format(messages.getMessage("license.delete.message", null, null),licenseId);
        return responseMessage;

    }

    public List<License> getLicensesByOrganization(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        return switch (clientType) {
            case "feign" -> {
                System.out.println("I am using the feign client");
                yield organizationFeignClient.getOrganization(organizationId);
            }
            case "rest" -> {
                System.out.println("I am using the rest client");
                yield organizationRestTemplateClient.getOrganization(organizationId);
            }
            case "discovery" -> {
                System.out.println("I am using the discovery client");
                yield organizationDiscoveryClient.getOrganization(organizationId);
            }
            default -> organizationRestTemplateClient.getOrganization(organizationId);
        };
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
//            responseMessage = String.format("This is the post and the object is: %s", license.toString());
            responseMessage = String.format(
                    messages.getMessage("license.create.message", null, locale),
                    license.toString()
            );
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
//            responseMessage = String.format("This is the put and the object is: %s", license.toString());
            responseMessage = String.format(
                    messages.getMessage("license.update.message", null, null),
                    license.toString()
            );
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);
        return responseMessage;
    }
}