package com.levgod.microservicioapi.DTO;

import java.util.Set;

public record ServiceDTO(String label, String icon, String routerLink, Set<InternalServiceDTO> internalServices) {
}
