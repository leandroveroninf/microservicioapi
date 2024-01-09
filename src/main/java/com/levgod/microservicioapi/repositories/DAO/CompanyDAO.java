package com.levgod.microservicioapi.repositories.DAO;

import com.levgod.microservicioapi.entities.*;
import com.levgod.microservicioapi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class CompanyDAO {

    @Autowired
    private CompanyRespository companyRespository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private InternalServiceRepository internalServiceRepository;

    @Autowired
    private BossesRespository bossesRespository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ChargeOfCompanyRepository ofCompanyRepository;

    private static final Logger logger = Logger.getLogger(CompanyDAO.class.getName());


    public Company findByIdCompany(Long idCompany){
        try{
            return this.companyRespository.findById(idCompany).orElse(null);
        }catch (Exception e){
            logger.warning("Error al buscar la compania: "+e.getMessage());
            return null;
        }
    }

    public List<Company> findAllCompany(){
        try{
            return this.companyRespository.findAll();
        }catch (Exception e){
            logger.warning("Error al buscar la compania: "+e.getMessage());
            return null;
        }
    }


    public Company saveCompany(Company company){
        try {
            return this.companyRespository.save(company);
        }catch (Exception e){
            System.out.println("Error al guardar la compania: ");
            logger.warning("Error al guardar la compania: "+e.getMessage());
            return null;
        }
    }


    // Agregamos los jefes de la compania
    public void addBossse(Long idCompany, Bosses boss){
        try{
            Company company = this.companyRespository.findById(idCompany).orElse(null);

            if(company != null){
                boss.setMyCompany(company);
                company.getBosses().add(boss);
                this.companyRespository.save(company);
                this.bossesRespository.save(boss);
            }

        }catch (Exception e){
            System.out.println("Error al agregar el jefe de la compania: "+ e);
            logger.warning("Error al agregar el jefe de la compania: "+e.getMessage());
        }
    }

    public void addAllBosses(Long idCompany, Set<Bosses> bosses){

        try{
            Company company = this.companyRespository.findById(idCompany).orElse(null);
            if(company != null){
                bosses.forEach(bs -> {
                    bs.setMyCompany(company);
                    this.bossesRespository.save(bs);
                });
                company.getBosses().addAll(bosses);
                this.companyRespository.save(company);
            }
        }catch (Exception e){
            System.out.println("Error al agregar los jefes a la compania: "+ e);
            logger.warning("Error al agregar los jefes a la compania: "+e.getMessage());
        }
    }


    // Agregamos los encargados de la compania
    public void addChargeOfCompany(Long idCompany, ChargeOfCompany ofCompany){
        try{
            Company company = this.companyRespository.findById(idCompany).orElse(null);

            if(company != null){
                ofCompany.setCompany(company);
                company.getChargeOfCompanies().add(ofCompany);
                this.companyRespository.save(company);
                this.ofCompanyRepository.save(ofCompany);
            }

        }catch (Exception e){
            System.out.println("Error al agregar un encargado a la compania: "+ e);
            logger.warning("Error al agregar un encargado a la compania: "+e.getMessage());
        }
    }

    public void addAllChargeOfCompany(Long idCompany, Set<ChargeOfCompany> ofCompanies){

        try{
            Company company = this.companyRespository.findById(idCompany).orElse(null);
            if(company != null){
                ofCompanies.forEach(ofCompany -> {
                    ofCompany.setCompany(company);
                    this.ofCompanyRepository.save(ofCompany);
                });
                company.getChargeOfCompanies().addAll(ofCompanies);
                this.companyRespository.save(company);
            }
        }catch (Exception e){
            System.out.println("Error al agregar los encargado a la compania: "+ e);
            logger.warning("Error al agregar los encargado a la compania: "+e.getMessage());
        }
    }



    // Agregamos los empleados de la compania
    public void addEmployee(Long idCompany, Employee employee){
        try{
            Company company = this.companyRespository.findById(idCompany).orElse(null);

            if(company != null){
                company.getEmployees().add(employee);
                employee.setMyCompany(company);
                this.employeeRepository.save(employee);
                this.companyRespository.save(company);
            }

        }catch (Exception e){
            System.out.println("Error al agregar un empleado a la compania: "+ e);
            logger.warning("Error al agregar un empleado a la compania: "+e.getMessage());
        }
    }

    public void addAllEmployee(Long idCompany, Set<Employee> employees){

        try{
            Company company = this.companyRespository.findById(idCompany).orElse(null);
            if(company != null){

                employees.forEach(employee -> {
                    employee.setMyCompany(company);
                    this.employeeRepository.save(employee);
                });

                company.getEmployees().addAll(employees);
                this.companyRespository.save(company);
            }
        }catch (Exception e){
            System.out.println("Error al agregar los empleados a la compania: "+ e);
            logger.warning("Error al agregar los empleados a la compania: "+e.getMessage());
        }
    }


    // Agregamos los servicios que tendra la compania
    public void addService(Long idCompany, Long idService){
        try{

            Company company = this.companyRespository.findById(idCompany).orElse(null);
            Services services = this.servicesRepository.findById(idService).orElse(null);
            if(company != null && services != null){
                company.getMyServices().add(services);
                services.getCompanies().add(company);

                this.companyRespository.save(company);
                this.servicesRepository.save(services);
            }
        }catch (Exception e){
            logger.warning("Error al agregar el servicio a la compania: "+e.getMessage());
        }

    }

    public void addAllService(Long idCompany, List<Long> idServices){
        try{

            Company company = this.companyRespository.findById(idCompany).orElse(null);
            if(company != null){
                Set<Services> services = new HashSet<>();
                for(Long idService : idServices){
                    Services service = this.servicesRepository.findById(idService).orElse(null);

                    if(service != null){
                        services.add(service);
                        service.getCompanies().add(company);
                        this.servicesRepository.save(service);
                    }
                }
                company.getMyServices().addAll(services);
                this.companyRespository.save(company);

            }
        }catch (Exception e){
            logger.warning("Error al agregar el servicio a la compania: "+e.getMessage());
        }

    }



    // Agregamos los servicios internos que tendra la compania
    public void addInternalService(Long idCompany, Long idInternalService) {
        try {

            Company company = this.companyRespository.findById(idCompany).orElse(null);


            if (company != null) {


                List<Services> servicesList = this.servicesRepository.findAll();

                outerLoop:
                for (Services myServices : company.getMyServices()) {
                    for (Services services : servicesList) {
                        if (myServices.getId().equals(services.getId())) {
                            for (InternalService internalService : services.getInternalServices()) {
                                if (internalService.getId().equals(idInternalService)) {
                                    company.getMyServicesInternal().add(internalService);
                                    internalService.getCompanies().add(company);
                                    this.companyRespository.save(company);
                                    this.internalServiceRepository.save(internalService);
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
            }


        } catch (Exception e) {
            logger.warning("Error al agregar el internal servicios a la compania: " + e.getMessage());
        }

    }

    public void addAllInternalService(Long idCompany, List<Long> idInternalServices){
        try{

            Company company = this.companyRespository.findById(idCompany).orElse(null);
            if (company != null) {
                List<Services> servicesList = this.servicesRepository.findAll();

                Set<InternalService> internalServices = company.getMyServices().stream()
                        .flatMap(myServices ->
                                servicesList.stream()
                                        .filter(services -> myServices.getId().equals(services.getId()))
                                        .flatMap(services -> services.getInternalServices().stream()
                                                .filter(internalService -> idInternalServices.contains(internalService.getId()))
                                                .peek(internalService -> {
                                                    internalService.getCompanies().add(company);
                                                    this.internalServiceRepository.save(internalService);
                                                })
                                        )
                        )
                        .collect(Collectors.toSet());

                company.getMyServicesInternal().addAll(internalServices);
                this.companyRespository.save(company);
            }

        }catch (Exception e){
            logger.warning("Error al agregar los internal service a la compania: "+e.getMessage());
        }
    }




}
