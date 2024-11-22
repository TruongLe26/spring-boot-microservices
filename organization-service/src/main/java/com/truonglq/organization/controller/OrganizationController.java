//package com.truonglq.organization.controller;
//
//import com.truonglq.organization.model.Organization;
//import com.truonglq.organization.service.OrganizationService;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value="v1/organization")
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor
//public class OrganizationController {
//
//    OrganizationService service;
//
//    @RequestMapping(value="/{organizationId}",method = RequestMethod.GET)
//    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
//        return ResponseEntity.ok(service.findById(organizationId));
//    }
//
//    @RequestMapping(value="/{organizationId}",method = RequestMethod.PUT)
//    public void updateOrganization( @PathVariable("organizationId") String id, @RequestBody Organization organization) {
//        service.update(organization);
//    }
//
//    @PostMapping
//    public ResponseEntity<Organization>  saveOrganization(@RequestBody Organization organization) {
//        return ResponseEntity.ok(service.create(organization));
//    }
//
//    @RequestMapping(value="/{organizationId}",method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteOrganization( @PathVariable("id") String id,  @RequestBody Organization organization) {
//        service.delete(organization);
//    }
//
//}