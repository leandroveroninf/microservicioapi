package com.levgod.microservicioapi.DTO;

import com.levgod.microservicioapi.entities.Services;

import java.util.Set;

public record UserDTO(String name, String las_name, String email, String DNI, Set<ServiceDTO> myServices, Set<ServiceDTO> totalService) {
}
