package com.truonglq.licensingservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "licenses")
@Getter @Setter @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class License extends RepresentationModel<License> {

    @Id
    @Column(name = "license_id", nullable = false)
    private String licenseId;
    @Column(name = "organization_id", nullable = false)
    private String organizationId;
    private String description;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "license_type", nullable = false)
    private String licenseType;
    @Column(name = "comment")
    private String comment;

    @Transient
    private String organizationName;
    @Transient
    private String contactName;
    @Transient
    private String contactPhone;
    @Transient
    private String contactEmail;


    public License withComment(String comment){
        this.setComment(comment);
        return this;
    }
}