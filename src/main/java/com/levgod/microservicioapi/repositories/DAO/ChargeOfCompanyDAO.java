package com.levgod.microservicioapi.repositories.DAO;

import com.levgod.microservicioapi.entities.*;
import com.levgod.microservicioapi.repositories.ChargeOfCompanyRepository;
import com.levgod.microservicioapi.repositories.InternalServiceRepository;
import com.levgod.microservicioapi.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Repository
public class ChargeOfCompanyDAO {

    @Autowired
    private ChargeOfCompanyRepository chargeOfCompanyRepository;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private InternalServiceRepository internalServiceRepository;

    private static final Logger logger = Logger.getLogger(ChargeOfCompanyDAO.class.getName());

    public void addService(Long idCompany, Long idService, Long idOfCompany){
        try{

            Company company = this.companyDAO
                    .findByIdCompany(idCompany);

            ChargeOfCompany ofCompany = company.getChargeOfCompanies()
                    .stream()
                    .filter(ep -> ep.getId().equals(idOfCompany)).toList().getFirst();


            Services services = company.getMyServices()
                    .stream()
                    .filter(s -> s.getId().equals(idService)).toList().getFirst();

            if(ofCompany != null && services != null){
                ofCompany.setCompany(company);
                ofCompany.getMyServices().add(services);
                services.getOfCompanies().add(ofCompany);

                this.servicesRepository.save(services);
                this.chargeOfCompanyRepository.save(ofCompany);
            }



        }catch (Exception e){

        }
    }

    public void addAllService(Long idOfCompany, List<Long> idServices, Long idCompany){
        try{

            Company company = this.companyDAO
                    .findByIdCompany(idCompany);

            ChargeOfCompany ofCompany = company.getChargeOfCompanies()
                    .stream()
                    .filter(ep -> ep.getId().equals(idOfCompany)).toList().getFirst();


            Set<Services> services = new HashSet<>(company.getMyServices()
                    .stream()
                    .filter(s -> idServices.contains(s.getId())).toList());

            if(ofCompany != null && !services.isEmpty()){
                ofCompany.getMyServices().addAll(services);

                services.forEach(services1 -> {
                    services1.getOfCompanies().add(ofCompany);
                    this.servicesRepository.save(services1);
                });

                this.chargeOfCompanyRepository.save(ofCompany);
            }



        }catch (Exception e){

        }
    }

    public void addInternalService(Long idOfCompany, Long idService, Long idCompany, Long idInternalService){
        try{

            Company company = this.companyDAO
                    .findByIdCompany(idCompany);

            ChargeOfCompany ofCompany = company.getChargeOfCompanies()
                    .stream()
                    .filter(ep -> ep.getId().equals(idOfCompany)).toList().getFirst();


            Services services = company.getMyServices()
                    .stream()
                    .filter(s -> s.getId().equals(idService)).toList().getFirst();

            InternalService internalService = company.getMyServicesInternal()
                    .stream()
                    .filter(si -> si.getId().equals(idInternalService)).toList().getFirst();

            if(ofCompany != null && services != null && internalService != null){
                ofCompany.getMyServicesInternal().add(internalService);
                internalService.getOfCompanies().add(ofCompany);
                this.chargeOfCompanyRepository.save(ofCompany);
                this.internalServiceRepository.save(internalService);
            }



        }catch (Exception e){

        }
    }

    public void addAllInternalService(Long idOfCompany, List<Long> idServices, Long idCompany, List<Long> idInternalServices){
        try{

            Company company = this.companyDAO
                    .findByIdCompany(idCompany);

            ChargeOfCompany ofCompany = company.getChargeOfCompanies()
                    .stream()
                    .filter(ep -> ep.getId().equals(idOfCompany)).toList().getFirst();


            Set<Services> services = new HashSet<>(company.getMyServices()
                    .stream()
                    .filter(s -> idServices.contains(s.getId())).toList());

            Set<InternalService> internalServices = new HashSet<>(company.getMyServicesInternal()
                    .stream()
                    .filter(SI -> idInternalServices.contains(SI.getId())).toList());

            if(ofCompany != null && !services.isEmpty() && !internalServices.isEmpty()){
                ofCompany.getMyServicesInternal().addAll(internalServices);

                internalServices.forEach(internalService -> {
                    internalService.getOfCompanies().add(ofCompany);
                    this.internalServiceRepository.save(internalService);
                });

                this.chargeOfCompanyRepository.save(ofCompany);
            }



        }catch (Exception e){

        }
    }





}
