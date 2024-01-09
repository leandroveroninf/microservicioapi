package com.levgod.microservicioapi.DTO.internalServices;

import com.levgod.microservicioapi.DTO.Convert;
import com.levgod.microservicioapi.entities.InternalService;
import org.springframework.stereotype.Service;

@Service
public class InternalServiceConvert implements Convert<InternalServiceDTO, InternalService> {


    @Override
    public InternalServiceDTO convertDTO(InternalService entity) {

        return new InternalServiceDTO( entity.getId(), entity.getLabel(), entity.getIcon(), entity.getRouterLink());
    }

    @Override
    public InternalService convertToEntity(InternalServiceDTO dto) {
        return null;
    }
}
