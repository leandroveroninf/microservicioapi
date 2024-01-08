package com.levgod.microservicioapi.entities;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String dni;
    private String email;

    @ManyToMany(mappedBy = "users")
    @JsonBackReference
    private Set<Services> myServices = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "users_internal_service",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "internal_service_id"))
    @JsonIgnoreProperties
    private Set<InternalService> myServicesInternal = new HashSet<>();

}
