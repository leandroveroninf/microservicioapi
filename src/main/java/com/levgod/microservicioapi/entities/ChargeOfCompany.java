package com.levgod.microservicioapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "charge_of_company")
public class ChargeOfCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String dni;
    private String email;

    @ManyToOne
    @JoinColumn(name = "chargeOfCompany_id")
    @JsonIgnore
    private Company company;


    @ManyToMany(mappedBy = "ofCompanies")
    @JsonIgnore
    private Set<Services> myServices = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "charge_of_company_internal_service",
            joinColumns = @JoinColumn(name = "charge_of_company_id"),
            inverseJoinColumns = @JoinColumn(name = "internal_service_id"))
    @JsonIgnore
    private Set<InternalService> myServicesInternal = new HashSet<>();

}
