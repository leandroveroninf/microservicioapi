package com.levgod.microservicioapi.DTO.services;

public record SimpleServiceDTO(
        Long id,
        String label,
        String icon,
        String routerLink
) {
}
