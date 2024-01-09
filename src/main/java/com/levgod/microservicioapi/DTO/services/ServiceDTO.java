package com.levgod.microservicioapi.DTO.services;

import com.levgod.microservicioapi.DTO.company.CompanyDTO;
import com.levgod.microservicioapi.DTO.company.SimpleCompanyDTO;
import com.levgod.microservicioapi.DTO.internalServices.InternalServiceDTO;

import java.util.Set;

public record ServiceDTO(
        Long id,
        String label,
        String icon,
        String routerLink,
        Set<InternalServiceDTO> internalServices,
        Set<SimpleCompanyDTO> companies
) {
}
