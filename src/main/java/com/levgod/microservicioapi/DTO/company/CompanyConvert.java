package com.levgod.microservicioapi.DTO.company;

import com.levgod.microservicioapi.DTO.*;
import com.levgod.microservicioapi.DTO.bossess.BossesConvert;
import com.levgod.microservicioapi.DTO.bossess.BossesDTO;
import com.levgod.microservicioapi.DTO.chargeOfCompany.ChargeOfCompanyConvert;
import com.levgod.microservicioapi.DTO.chargeOfCompany.ChargeOfCompanyDTO;
import com.levgod.microservicioapi.DTO.employees.EmployeeConvert;
import com.levgod.microservicioapi.DTO.employees.EmployeeDTO;
import com.levgod.microservicioapi.DTO.internalServices.InternalServiceConvert;
import com.levgod.microservicioapi.DTO.internalServices.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.services.ServiceConvert;
import com.levgod.microservicioapi.DTO.services.ServiceDTO;
import com.levgod.microservicioapi.DTO.services.SimpleServiceDTO;
import com.levgod.microservicioapi.entities.Company;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyConvert implements Convert<CompanyDTO, Company> {

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
    public CompanyDTO convertDTO(Company entity) {
        BossesConvert bossesConvert = new BossesConvert();
        EmployeeConvert employeeConvert = new EmployeeConvert();
        ChargeOfCompanyConvert ofCompanyConvert = new ChargeOfCompanyConvert();

        Set<BossesDTO> bossesDTO = new HashSet<>(
                entity.getBosses().stream()
                        .map(bossesConvert::convertDTO).toList()
        );

        Set<ChargeOfCompanyDTO> ofCompanyDTOS = new HashSet<>(
                entity.getChargeOfCompanies()
                        .stream().map(ofCompanyConvert::convertDTO).toList()
        );

        Set<EmployeeDTO> employeeDTO = new HashSet<>(
                entity.getEmployees()
                        .stream().map(employeeConvert::convertDTO).toList()
        );



        Set<ServiceDTO> myServices = this.innerJoinMyServiceAndMyIneterServices(entity.getMyServices(), entity.getMyServicesInternal());

        return new CompanyDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getIconCompany(),
                bossesDTO,
                ofCompanyDTOS,
                employeeDTO,
                myServices);
    }

    public SimpleCompanyDTO convertDTOSimple(Company entity){
        BossesConvert bossesConvert = new BossesConvert();
        Set<BossesDTO> bossesDTO = new HashSet<>(
                entity.getBosses().stream()
                        .map(bossesConvert::convertDTO).toList()
        );
        return new SimpleCompanyDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getIconCompany(),
                bossesDTO
        );
    }

    @Override
    public Company convertToEntity(CompanyDTO dto) {
        return null;
    }


}
