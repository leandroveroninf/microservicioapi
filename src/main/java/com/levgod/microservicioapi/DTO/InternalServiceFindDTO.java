package com.levgod.microservicioapi.DTO;

import java.util.Set;

public record InternalServiceFindDTO(String label, String icon, String routerLink, Set<UserSimpleDTO> users) {
}
