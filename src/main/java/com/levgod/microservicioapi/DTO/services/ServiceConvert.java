package com.levgod.microservicioapi.DTO.services;

import com.levgod.microservicioapi.DTO.Convert;
import com.levgod.microservicioapi.DTO.company.CompanyConvert;
import com.levgod.microservicioapi.DTO.company.SimpleCompanyDTO;
import com.levgod.microservicioapi.DTO.internalServices.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.internalServices.InternalServiceConvert;
import com.levgod.microservicioapi.entities.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceConvert implements Convert<ServiceDTO, Services> {



    @Override
    public ServiceDTO convertDTO(Services services){
        InternalServiceConvert internalServiceConvert = new InternalServiceConvert();
        CompanyConvert companyConvert = new CompanyConvert();

        Set<InternalServiceDTO> internalServiceDTOS = new HashSet<>(
                services.getInternalServices().stream()
                        .map(internalServiceConvert::convertDTO).toList()

        );

        Set<SimpleCompanyDTO> simpleCompanyDTOS = new HashSet<>(
                services.getCompanies().stream()
                        .map(companyConvert::convertDTOSimple).toList()
        );

    return new ServiceDTO(
            services.getId(),
            services.getLabel(),
            services.getIcon(),
            services.getRouterLink(),
            internalServiceDTOS,
            simpleCompanyDTOS);

    }

    public SimpleServiceDTO convertDTOSimple(Services services){
        return new SimpleServiceDTO( services.getId(),services.getLabel(), services.getIcon(), services.getRouterLink());
    }

    @Override
    public Services convertToEntity(ServiceDTO dto) {
        return null;
    }





}
