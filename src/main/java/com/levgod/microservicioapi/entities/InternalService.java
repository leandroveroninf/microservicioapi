package com.levgod.microservicioapi.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "internal_service")
public class InternalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String icon;
    private String routerLink;

    @ManyToOne
    @JoinColumn(name = "internalService_id")
    @JsonBackReference
    private Services services;


    @ManyToMany(mappedBy = "myServicesInternal")
    @JsonIgnoreProperties
    private Set<User> usersInternalService = new HashSet<>();


    @ManyToMany(mappedBy = "myServicesInternal")
    @JsonIgnoreProperties
    private Set<Employee> employeesInternalService = new HashSet<>();

    @ManyToMany(mappedBy = "myServicesInternal")
    @JsonIgnoreProperties
    private Set<Company> companies = new HashSet<>();


}
