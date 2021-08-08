package com.example.employee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity {

    @Id
    @Column(name = "ADDRESS_ID")
    private long id;

    @Column(name = "ADDRESS_LINE", length=100)
    private String addressLine;

    @Column(name = "STREET", length=100)
    private String street;

    @Column(name = "CITY", length=50)
    private String city;

    @Column(name = "DISTRICT", length=50)
    private String district;

    @Column(name = "COUNTRY", length=10)
    private String country;

    @Column(name = "PIN_CODE", length=10, nullable=false)
    private String pincode;

    @ManyToOne
    @JoinColumn(name="STUDENT_ID")
    private EmployeeEntity studentEntity;
}
