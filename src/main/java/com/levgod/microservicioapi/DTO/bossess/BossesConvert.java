package com.levgod.microservicioapi.DTO.bossess;

import com.levgod.microservicioapi.DTO.*;
import com.levgod.microservicioapi.DTO.internalServices.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.services.ServiceDTO;
import com.levgod.microservicioapi.entities.Bosses;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class  BossesConvert implements Convert<BossesDTO, Bosses> {


    @Override
    public BossesDTO convertDTO(Bosses entity) {

        return new BossesDTO(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDni()
        );
    }

    @Override
    public Bosses convertToEntity(BossesDTO dto) {
        return null;
    }
}
