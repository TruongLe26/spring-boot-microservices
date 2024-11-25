package com.truonglq.licensingservice.repository;

import com.truonglq.licensingservice.model.License;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LicenseRepository extends CrudRepository<License, String> {
    List<License> findByOrganizationId(String organizationId);
    @Query("SELECT l FROM License l WHERE l.licenseId = :licenseId AND l.organizationId = :organizationId")
    Optional<License> findByLicenseIdAndOrganizationId(@Param("organizationId") String organizationId, @Param("licenseId") String licenseId);
}