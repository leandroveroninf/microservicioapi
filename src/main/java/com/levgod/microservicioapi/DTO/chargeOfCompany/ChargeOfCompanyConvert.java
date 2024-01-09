package com.levgod.microservicioapi.DTO.chargeOfCompany;

import com.levgod.microservicioapi.DTO.Convert;
import com.levgod.microservicioapi.DTO.employees.EmployeeDTO;
import com.levgod.microservicioapi.entities.ChargeOfCompany;
import org.springframework.stereotype.Service;

@Service
public class ChargeOfCompanyConvert implements Convert<ChargeOfCompanyDTO, ChargeOfCompany> {
    @Override
    public ChargeOfCompanyDTO convertDTO(ChargeOfCompany entity) {
        return new ChargeOfCompanyDTO(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDni(),
                entity.getMyServices(),
                entity.getMyServicesInternal()
        );
    }

    @Override
    public ChargeOfCompany convertToEntity(ChargeOfCompanyDTO dto) {
        return null;
    }
}
