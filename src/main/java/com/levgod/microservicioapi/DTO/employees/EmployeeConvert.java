package com.levgod.microservicioapi.DTO.employees;

import com.levgod.microservicioapi.DTO.Convert;
import com.levgod.microservicioapi.DTO.internalServices.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.services.ServiceDTO;
import com.levgod.microservicioapi.entities.Employee;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeConvert implements Convert<EmployeeDTO, Employee> {

    private Set<ServiceDTO> innerJoinMyServiceAndMyIneterServices(Set<Services> services, Set<InternalService> internalServices) {
        Set<ServiceDTO> myServices = new HashSet<>();

        for (Services service : services) {
            Set<InternalServiceDTO> internalServicesDtos = new HashSet<>();
            for (InternalService internalService : internalServices) {

                service.getInternalServices().forEach(IS -> {
                    if (IS.getId().equals(internalService.getId())) {
                        internalServicesDtos.add(
                                new InternalServiceDTO(
                                        internalService.getId(),
                                        internalService.getLabel(),
                                        internalService.getIcon(),
                                        internalService.getRouterLink()
                                )
                        );
                    }
                });
            }

            myServices.add(
                    new ServiceDTO(
                            service.getId(),
                            service.getLabel(),
                            service.getIcon(),
                            service.getRouterLink(),
                            internalServicesDtos,
                            null
                    )
            );

        }

        return myServices;

    }


    @Override
    public EmployeeDTO convertDTO(Employee entity) {

        Set<ServiceDTO> myServices = this.innerJoinMyServiceAndMyIneterServices(entity.getMyServices(), entity.getMyServicesInternal());
        return new EmployeeDTO(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDni(),
                myServices
                );
    }

    @Override
    public Employee convertToEntity(EmployeeDTO dto) {
        return null;
    }
}
