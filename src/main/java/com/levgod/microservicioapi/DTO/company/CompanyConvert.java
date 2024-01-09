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
import com.levgod.microservicioapi.DTO.services.SimpleServiceDTO;
import com.levgod.microservicioapi.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyConvert implements Convert<CompanyDTO, Company> {


    @Override
    public CompanyDTO convertDTO(Company entity) {
        BossesConvert bossesConvert = new BossesConvert();
        InternalServiceConvert internalServiceConvert = new InternalServiceConvert();
        ServiceConvert serviceConvert = new ServiceConvert();
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

        Set<InternalServiceDTO> internalServiceDTOS = new HashSet<>(
                entity.getMyServicesInternal().stream()
                        .map(internalServiceConvert::convertDTO).toList()
        );

        Set<SimpleServiceDTO> simpleServiceDTOS = new HashSet<>(
                entity.getMyServices().stream()
                        .map(serviceConvert::convertDTOSimple)
                        .toList()
        );

        return new CompanyDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getIconCompany(),
                bossesDTO,
                ofCompanyDTOS,
                employeeDTO,
                simpleServiceDTOS,
                internalServiceDTOS);
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
