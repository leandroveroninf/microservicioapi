package com.levgod.microservicioapi.DTO.employees;

import com.levgod.microservicioapi.DTO.Convert;
import com.levgod.microservicioapi.entities.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConvert implements Convert<EmployeeDTO, Employee> {
    @Override
    public EmployeeDTO convertDTO(Employee entity) {

        return new EmployeeDTO(
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
    public Employee convertToEntity(EmployeeDTO dto) {
        return null;
    }
}
