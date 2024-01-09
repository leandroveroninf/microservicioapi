package com.levgod.microservicioapi.DTO.services;

import com.levgod.microservicioapi.DTO.internalServices.InternalServiceFindDTO;

import java.util.Set;

public record ServiceFindDTO(
        Long id,
        String label,
        String icon,
        String routerLink,
        Set<InternalServiceFindDTO> internalServices) {
}
