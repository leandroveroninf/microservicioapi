package com.levgod.microservicioapi.DTO;

import java.util.Set;

public record ServiceFindDTO(String label, String icon, String routerLink, Set<InternalServiceFindDTO> internalServices, Set<UserSimpleDTO> user) {
}
