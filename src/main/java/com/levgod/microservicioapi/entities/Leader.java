package com.levgod.microservicioapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "leader")
public class Leader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String post;

    @ManyToMany(mappedBy = "leader")
    @JsonIgnore
    private Set<Services> myServices = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "leader_internal_service",
            joinColumns = @JoinColumn(name = "leader_id"),
            inverseJoinColumns = @JoinColumn(name = "internal_service_id"))
    @JsonIgnore
    private Set<InternalService> myServicesInternal = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "leader_id")
    @JsonIgnore
    private Company myCompany;



}
