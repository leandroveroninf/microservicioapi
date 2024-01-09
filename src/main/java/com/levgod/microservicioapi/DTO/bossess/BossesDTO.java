package com.levgod.microservicioapi.DTO.bossess;

import com.levgod.microservicioapi.DTO.internalServices.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.services.ServiceDTO;

import java.util.Set;

public record BossesDTO (
        Long id, String name, String las_name, String email, String DNI
){
}
