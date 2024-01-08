package com.levgod.microservicioapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
            name = "user_services",
            joinColumns = @JoinColumn(name = "services_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))// Anotación en el lado "manejado" de la relación
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "employee_services",
            joinColumns = @JoinColumn(name = "services_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))// Anotación en el lado "manejado" de la relación
    private Set<Employee> employees = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "company_services",
            joinColumns = @JoinColumn(name = "services_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))// Anotación en el lado "manejado" de la relación
    private Set<Company> companies = new HashSet<>();

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private Set<InternalService> internalServices = new HashSet<>();



}
