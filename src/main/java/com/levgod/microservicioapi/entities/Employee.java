package com.levgod.microservicioapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String post;

    @ManyToMany(mappedBy = "employees")
    @JsonBackReference
    private Set<Services> myServices = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "employee_internal_service",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "internal_service_id"))
    @JsonIgnoreProperties
    private Set<InternalService> myServicesInternal = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Company myCompany;


}
