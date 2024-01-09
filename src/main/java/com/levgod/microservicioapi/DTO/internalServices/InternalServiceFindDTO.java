package com.levgod.microservicioapi.DTO.internalServices;

import java.util.Set;

public record InternalServiceFindDTO(
        Long id,
        String label,
        String icon,
        String routerLink) {
}
