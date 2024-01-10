package com.levgod.microservicioapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String iconCompany;
    private String address;


    // Jefes
    @OneToMany(mappedBy = "myCompany", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Bosses> bosses = new HashSet<>();

    // Encargados
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ChargeOfCompany> chargeOfCompanies = new HashSet<>();

    // Lider
    @OneToMany(mappedBy = "myCompany", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Leader> leaders = new HashSet<>();

    // Empleados
    @OneToMany(mappedBy = "myCompany", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

    // Servicios generales
    @ManyToMany(mappedBy = "companies")
    @JsonIgnore
    private Set<Services> myServices = new HashSet<>();

    // Servicios internos
    @ManyToMany
    @JoinTable(
            name = "company_internal_service",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "internal_service_id"))
    @JsonIgnore
    private Set<InternalService> myServicesInternal = new HashSet<>();

}
