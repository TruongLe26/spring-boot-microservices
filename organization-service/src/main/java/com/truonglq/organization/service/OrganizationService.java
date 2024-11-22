//package com.truonglq.organization.service;
//
//import com.truonglq.organization.model.Organization;
//import com.truonglq.organization.repository.OrganizationRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor
//public class OrganizationService {
//
//    OrganizationRepository repository;
//
//    public Organization findById(String organizationId) {
//        Optional<Organization> opt = repository.findById(organizationId);
//        return opt.orElse(null);
//    }
//
//    public Organization create(Organization organization){
//        organization.setId( UUID.randomUUID().toString());
//        organization = repository.save(organization);
//        return organization;
//
//    }
//
//    public void update(Organization organization){
//        repository.save(organization);
//    }
//
//    public void delete(Organization organization){
//        repository.deleteById(organization.getId());
//    }
//}