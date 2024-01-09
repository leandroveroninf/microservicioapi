package com.levgod.microservicioapi.DTO.internalServices;

public record InternalServiceDTO (
        Long id,
        String label,
        String icon,
        String routerLink
){}
