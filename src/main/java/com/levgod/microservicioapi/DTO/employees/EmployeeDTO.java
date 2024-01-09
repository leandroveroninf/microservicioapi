package com.levgod.microservicioapi.DTO.employees;

import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;

import java.util.Set;

public record EmployeeDTO(
        Long id, String name, String las_name, String email, String DNI,
        Set<Services> myServices,
        Set<InternalService> myInternalServices
) {
}
