package com.levgod.microservicioapi.DTO.company;

import com.levgod.microservicioapi.DTO.internalServices.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.services.ServiceDTO;
import com.levgod.microservicioapi.DTO.bossess.BossesDTO;
import com.levgod.microservicioapi.DTO.services.SimpleServiceDTO;

import java.util.Set;

public record CompanyDTO (
        Long id,
        String name,
        String email,
        String address,
        String icon,
        Set<BossesDTO> bossesDTO,
        Set<SimpleServiceDTO> simpleServiceDTOS,
        Set<InternalServiceDTO> internalServiceDTOS
        ) {
}
