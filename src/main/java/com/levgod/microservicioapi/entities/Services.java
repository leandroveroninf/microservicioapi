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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_general")
public class Services implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String icon;
    private String routerLink;

    @ManyToMany
    @JoinTable(
            name = "leaders_services",
            joinColumns = @JoinColumn(name = "services_id"),
            inverseJoinColumns = @JoinColumn(name = "leader_id"))// Anotación en el lado "manejado" de la relación
    @JsonIgnore
    private Set<Leader> leaders = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "employee_services",
            joinColumns = @JoinColumn(name = "services_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))// Anotación en el lado "manejado" de la relación
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "ofCompanies_services",
            joinColumns = @JoinColumn(name = "services_id"),
            inverseJoinColumns = @JoinColumn(name = "ofCompanies_id"))
    @JsonIgnore
    private Set<ChargeOfCompany> ofCompanies = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "company_services",
            joinColumns = @JoinColumn(name = "services_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))// Anotación en el lado "manejado" de la relación
    @JsonIgnore
    private Set<Company> companies = new HashSet<>();

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<InternalService> internalServices = new HashSet<>();



}
