package com.levgod.microservicioapi.DTO.company;

import com.levgod.microservicioapi.DTO.bossess.BossesDTO;

import java.util.Set;

public record SimpleCompanyDTO(
        Long id,
        String name,
        String email,
        String address,
        String icon,
        Set<BossesDTO> bossesDTO) {
}
