package com.levgod.microservicioapi.services;

import com.levgod.microservicioapi.DTO.company.CompanyConvert;
import com.levgod.microservicioapi.DTO.company.CompanyDTO;
import com.levgod.microservicioapi.entities.*;
import com.levgod.microservicioapi.repositories.DAO.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class CompanyService {

    private static final Logger logger = Logger.getLogger(CompanyService.class.getName());
    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private CompanyConvert companyConvert;

    public CompanyDTO findByIdCompany(Long idCompany){
        try{
            return this.companyConvert.convertDTO(this.companyDAO.findByIdCompany(idCompany));
        }catch (Exception e){
            logger.warning("Error al buscar la compania: "+e.getMessage());
            return null;
        }
    }

    public Set<CompanyDTO> findAllCompany(){
        try{
            return new HashSet<>(
                    this.companyDAO.findAllCompany().stream()
                            .map(this.companyConvert::convertDTO).toList()
            );
        }catch (Exception e){
            logger.warning("Error al buscar la compania: "+e.getMessage());
            return null;
        }
    }

    public CompanyDTO saveCompany(Company company){
        try {
            return this.companyConvert.convertDTO(this.companyDAO.saveCompany(company));
        }catch (Exception e){
            System.out.println("Error al guardar la compania: ");
            logger.warning("Error al guardar la compania: "+e.getMessage());
            return null;
        }
    }


    // Agregamos los jefes de la compania
    public void addBossse(Long idCompany, Bosses boss){
        try{
            this.companyDAO.addBossse(idCompany, boss);
        }catch (Exception e){
            System.out.println("Error al agregar el jefe de la compania: "+ e);
            logger.warning("Error al agregar el jefe de la compania: "+e.getMessage());
        }
    }

    public void addAllBosses(Long idCompany, Set<Bosses> bosses){

        try{
            this.companyDAO.addAllBosses(idCompany, bosses);
        }catch (Exception e){
            System.out.println("Error al agregar los jefes a la compania: "+ e);
            logger.warning("Error al agregar los jefes a la compania: "+e.getMessage());
        }
    }


    // Agregamos los encargados de la compania
    public void addChargeOfCompany(Long idCompany, ChargeOfCompany ofCompany){
        try{
            this.companyDAO.addChargeOfCompany(idCompany, ofCompany);

        }catch (Exception e){
            System.out.println("Error al agregar un encargado a la compania: "+ e);
            logger.warning("Error al agregar un encargado a la compania: "+e.getMessage());
        }
    }

    public void addAllChargeOfCompany(Long idCompany, Set<ChargeOfCompany> ofCompanies){

        try{
            this.companyDAO.addAllChargeOfCompany(idCompany, ofCompanies);
        }catch (Exception e){
            System.out.println("Error al agregar los encargado a la compania: "+ e);
            logger.warning("Error al agregar los encargado a la compania: "+e.getMessage());
        }
    }


    public void addLeader(Long idCompany, Leader leader){
        try{
            this.companyDAO.addLeader(idCompany, leader);

        }catch (Exception e){
            System.out.println("Error al agregar un empleado a la compania: "+ e);
            logger.warning("Error al agregar un empleado a la compania: "+e.getMessage());
        }
    }

    public void addAllLeaders(Long idCompany, Set<Leader> leaders){

        try{
            this.companyDAO.addAllLeaders(idCompany, leaders);
        }catch (Exception e){
            System.out.println("Error al agregar los empleados a la compania: "+ e);
            logger.warning("Error al agregar los empleados a la compania: "+e.getMessage());
        }
    }



    // Agregamos los empleados de la compania
    public void addEmployee(Long idCompany, Employee employee){
        try{
            this.companyDAO.addEmployee(idCompany, employee);

        }catch (Exception e){
            System.out.println("Error al agregar un empleado a la compania: "+ e);
            logger.warning("Error al agregar un empleado a la compania: "+e.getMessage());
        }
    }

    public void addAllEmployee(Long idCompany, Set<Employee> employees){

        try{
            this.companyDAO.addAllEmployee(idCompany, employees);
        }catch (Exception e){
            System.out.println("Error al agregar los empleados a la compania: "+ e);
            logger.warning("Error al agregar los empleados a la compania: "+e.getMessage());
        }
    }


    // Agregamos los servicios que tendra la compania
    public void addService(Long idCompany, Long idService){
        try{

            this.companyDAO.addService(idCompany, idService);
        }catch (Exception e){
            logger.warning("Error al agregar el servicio a la compania: "+e.getMessage());
        }

    }

    public void addAllService(Long idCompany, List<Long> idServices){
        try{

            this.companyDAO.addAllService(idCompany, idServices);
        }catch (Exception e){
            logger.warning("Error al agregar el servicio a la compania: "+e.getMessage());
        }

    }



    // Agregamos los servicios internos que tendra la compania
    public void addInternalService(Long idCompany, Long idInternalService){
        try{

            this.companyDAO.addInternalService(idCompany, idInternalService);

        }catch (Exception e){
            logger.warning("Error al agregar el internal servicios a la compania: "+e.getMessage());
        }

    }

    public void addAllInternalService(Long idCompany, List<Long> idInternalServices){
        try{

            this.companyDAO.addAllInternalService(idCompany, idInternalServices);
        }catch (Exception e){
            logger.warning("Error al agregar los internal service a la compania: "+e.getMessage());
        }
    }




}
