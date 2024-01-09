package com.levgod.microservicioapi.services;

import com.levgod.microservicioapi.DTO.employees.EmployeeConvert;
import com.levgod.microservicioapi.DTO.employees.EmployeeDTO;
import com.levgod.microservicioapi.entities.Company;
import com.levgod.microservicioapi.entities.Employee;
import com.levgod.microservicioapi.repositories.DAO.CompanyDAO;
import com.levgod.microservicioapi.repositories.DAO.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private CompanyDAO companyService;

    @Autowired
    private EmployeeConvert employeeConvert;


    public EmployeeDTO findByUser(Long idEmployee, Long idCompany){
        try{
            Company company = this.companyService.findByIdCompany(idCompany);
            if(company != null){
                return this.employeeConvert.convertDTO(this.employeeDAO.findByIdEmployee(idEmployee));
            }
        }catch (Exception e){

            return null;
        }

        return null;
    }

    /*
       Agregamos un usuario y lo guardamos en la compania correspondiente
    */
    public EmployeeDTO save(Employee employee, Long idCompany){
        try{
            return this.employeeConvert.convertDTO(this.employeeDAO.save(employee, idCompany));
        }catch (Exception e){

            return null;
        }
    }

    /*
        Agregamos los servicios
     */
    public void addService(Long idEmployee, Long idService, Long idCompany){
        try{
            this.employeeDAO.addService(idEmployee, idService, idCompany);
        }catch (Exception e){
            System.out.println("error al agregar un servicio al empleado: "+e.getMessage());
        }
    }

    public void addAllService(Long idEmployee, List<Long> idServices, Long idCompany){
        try{
            this.employeeDAO.addAllService(idEmployee, idServices, idCompany);
        }catch (Exception e){
            System.out.println("error al agregar todos los servicio al empleado: "+e.getMessage());
        }
    }

    /*
        Agregamos servicios internos
    */
    public void addInternalService(Long idEmployee, Long idService, Long idCompany, Long idInternalService){
        try{
            this.employeeDAO.addInternalService(idEmployee, idService, idCompany, idInternalService);
        }catch (Exception e){
            System.out.println("error al agregar todos el servicio interno al empleado: "+e.getMessage());
        }
    }

    public void addAllInternalService(Long idEmployee, List<Long> idServices, Long idCompany, List<Long> idInternalServices){
        try{
            this.employeeDAO.addAllInternalService(idEmployee, idServices, idCompany, idInternalServices);
        }catch (Exception e){
            System.out.println("error al agregar todos los servicio internos al empleado: "+e.getMessage());
        }
    }




}
