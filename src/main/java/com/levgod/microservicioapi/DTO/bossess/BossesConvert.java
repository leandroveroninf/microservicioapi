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

        Set<ServiceDTO> serviceDTOS = new HashSet<>(
                entity.getMyCompany().getMyServices()
                        .stream().map(services -> new ServiceDTO(
                                services.getId(),
                                services.getLabel(),
                                services.getIcon(),
                                services.getRouterLink(),
                                null, null)
                        )
                        .toList()
        );

        Set<InternalServiceDTO> internalServiceDTOS = new HashSet<>(
                entity.getMyCompany().getMyServicesInternal().stream()
                        .map(is -> new InternalServiceDTO(is.getId(), is.getLabel(), is.getIcon(), is.getRouterLink()))
                        .toList()
        );


        return new BossesDTO(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDni(),
                serviceDTOS,
                internalServiceDTOS
        );
    }

    @Override
    public Bosses convertToEntity(BossesDTO dto) {
        return null;
    }
}
