package com.levgod.microservicioapi.services;

import com.levgod.microservicioapi.entities.ChargeOfCompany;
import com.levgod.microservicioapi.entities.Company;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.repositories.DAO.ChargeOfCompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChargeOfCompanyService {

    @Autowired
    private ChargeOfCompanyDAO ofCompanyDAO;


    public void addService(Long idCompany, Long idService, Long idOfCompany){
        try{
            this.ofCompanyDAO.addService(idCompany, idService, idOfCompany);

        }catch (Exception e){

        }
    }

    public void addAllService(Long idOfCompany, List<Long> idServices, Long idCompany){
        try{
            this.ofCompanyDAO.addAllService(idOfCompany, idServices, idCompany);
        }catch (Exception e){

        }
    }

    public void addInternalService(Long idOfCompany, Long idService, Long idCompany, Long idInternalService){
        try{
            this.ofCompanyDAO.addInternalService(idOfCompany, idService, idCompany, idInternalService);
        }catch (Exception e){

        }
    }

    public void addAllInternalService(Long idOfCompany, Long idServices, Long idCompany, List<Long> idInternalServices){
        try{
            this.ofCompanyDAO.addAllInternalService(idOfCompany, idServices, idCompany, idInternalServices);
        }catch (Exception e){

        }
    }





}
