package com.truonglq.licensingservice.controller;

import com.truonglq.licensingservice.model.License;
import com.truonglq.licensingservice.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @RequestMapping(value = "/{licenseId}/{clientType}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<License> getLicensesWithClient(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @PathVariable("clientType") String clientType
    ) {
        return ResponseEntity.ok(licenseService.getLicense(organizationId, licenseId, clientType));
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(@RequestBody License request) {
        return ResponseEntity.ok(licenseService.updateLicense(request));
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@RequestBody License request) {
        return ResponseEntity.ok(licenseService.createLicense(request));
    }

    @DeleteMapping(value="/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicensesByOrganization(organizationId);
    }

//    @GetMapping(value = "/{licenseId}")
//    public ResponseEntity<License> getLicense(
//            @PathVariable("organizationId") String organizationId,
//            @PathVariable("licenseId") String licenseId) {
//        License license = licenseService.getLicense(organizationId, licenseId);
//
//        license.add(
//                linkTo(methodOn(LicenseController.class)
//                        .getLicense(organizationId, license.getLicenseId()))
//                        .withSelfRel(),
//                linkTo(methodOn(LicenseController.class)
//                        .createLicense(organizationId, license, null))
//                        .withRel("createLicense"),
//                linkTo(methodOn(LicenseController.class)
//                        .updateLicense(organizationId, license))
//                        .withRel("updateLicense"),
//                linkTo(methodOn(LicenseController.class)
//                        .deleteLicense(organizationId, license.getLicenseId()))
//                        .withRel("deleteLicense"));
//
//        return ResponseEntity.ok(license);
//    }

//    @PutMapping
//    public ResponseEntity<String> updateLicense(
//            @PathVariable("organizationId") String organizationId,
//            @RequestBody License request
//    ) {
//        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
//    }

//    @PostMapping
//    public ResponseEntity<String> createLicense(
//            @PathVariable("organizationId") String organizationId,
//            @RequestBody License request,
//            @RequestHeader(value = "Accept-Language", required = false) Locale locale
//            ) {
//        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
//    }

//    @DeleteMapping(value = "/{licenseId}")
//    public ResponseEntity<String> deleteLicense(
//            @PathVariable("organizationId") String organizationId,
//            @PathVariable("licenseId") String licenseId
//    ) {
//        return ResponseEntity.ok(licenseService.deleteLicense(organizationId, licenseId));
//    }

}
