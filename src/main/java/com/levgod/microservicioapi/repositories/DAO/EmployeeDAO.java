package com.levgod.microservicioapi.repositories.DAO;

import com.levgod.microservicioapi.entities.Company;
import com.levgod.microservicioapi.entities.Employee;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.repositories.CompanyRespository;
import com.levgod.microservicioapi.repositories.EmployeeRepository;
import com.levgod.microservicioapi.repositories.InternalServiceRepository;
import com.levgod.microservicioapi.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class EmployeeDAO {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRespository companyRespository;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private ServicesDAO servicesDAO;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private InternalServiceRepository internalServiceRepository;


    private static final Logger logger = Logger.getLogger(EmployeeDAO.class.getName());

    public Employee findByIdEmployee(Long idEmployee){
        try{
            return this.employeeRepository.findById(idEmployee).orElse(null);

        }catch (Exception e){
            logger.warning("Error al consultar un empleado: "+e.getMessage());
        }
        return  null;
    }

    /*
        Agregamos un usuario y lo guardamos en la compania correspondiente
     */
    public Employee save(Employee employee, Long idCompany){
        try{
            Company company = this.companyDAO.findByIdCompany(idCompany);
            if(company != null){
                employee.setMyCompany(company);
                company.getEmployees().add(employee);
                this.companyRespository.save(company);
                return this.employeeRepository.save(employee);
            }
        }catch (Exception e){
            logger.warning("Error al consultar un empleado: "+e.getMessage());
        }
        return  null;
    }

    /*
        Agregamos los servicios
     */
    public void addService(Long idEmployee, Long idService, Long idCompany){
        try{

            Company company = this.companyDAO
                    .findByIdCompany(idCompany);

            Employee employee = company.getEmployees()
                    .stream()
                    .filter(ep -> ep.getId().equals(idEmployee)).toList().getFirst();


            Services services = company.getMyServices()
                    .stream()
                    .filter(s -> s.getId().equals(idService)).toList().getFirst();

            if(employee != null && services != null){
                employee.setMyCompany(company);
                employee.getMyServices().add(services);
                services.getEmployees().add(employee);

                this.servicesRepository.save(services);
                this.employeeRepository.save(employee);
            }



        }catch (Exception e){

        }
    }

    public void addAllService(Long idEmployee, List<Long> idServices, Long idCompany){
        try{

            Company company = this.companyDAO
                    .findByIdCompany(idCompany);

            Employee employee = company.getEmployees()
                    .stream()
                    .filter(ep -> ep.getId().equals(idEmployee)).toList().getFirst();


            Set<Services> services = new HashSet<>(company.getMyServices()
                    .stream()
                    .filter(s -> idServices.contains(s.getId())).toList());

            if(employee != null && !services.isEmpty()){
                employee.getMyServices().addAll(services);

                services.forEach(services1 -> {
                    services1.getEmployees().add(employee);
                    this.servicesRepository.save(services1);
                });

                this.employeeRepository.save(employee);
            }



        }catch (Exception e){

        }
    }

    /*
        Agregamos servicios internos
    */
    public void addInternalService(Long idEmployee, Long idService, Long idCompany, Long idInternalService){
        try{

            Company company = this.companyDAO
                    .findByIdCompany(idCompany);

            Employee employee = company.getEmployees()
                    .stream()
                    .filter(ep -> ep.getId().equals(idEmployee)).toList().getFirst();


            Services services = company.getMyServices()
                    .stream()
                    .filter(s -> s.getId().equals(idService)).toList().getFirst();

            InternalService internalService = company.getMyServicesInternal()
                    .stream()
                    .filter(si -> si.getId().equals(idInternalService)).toList().getFirst();

            if(employee != null && services != null && internalService != null){
                employee.getMyServicesInternal().add(internalService);
                internalService.getEmployeesInternalService().add(employee);
                this.employeeRepository.save(employee);
                this.internalServiceRepository.save(internalService);
            }



        }catch (Exception e){

        }
    }

    public void addAllInternalService(Long idEmployee, List<Long> idServices, Long idCompany, List<Long> idInternalServices){
        try{

            Company company = this.companyDAO
                    .findByIdCompany(idCompany);

            Employee employee = company.getEmployees()
                    .stream()
                    .filter(ep -> ep.getId().equals(idEmployee)).toList().getFirst();


            Set<Services> services = new HashSet<>(company.getMyServices()
                    .stream()
                    .filter(s -> idServices.contains(s.getId())).toList());

            Set<InternalService> internalServices = new HashSet<>(company.getMyServicesInternal()
                    .stream()
                    .filter(SI -> idInternalServices.contains(SI.getId())).toList());

            if(employee != null && !services.isEmpty() && !internalServices.isEmpty()){
                employee.getMyServicesInternal().addAll(internalServices);

                internalServices.forEach(internalService -> {
                    internalService.getEmployeesInternalService().add(employee);
                    this.internalServiceRepository.save(internalService);
                });

                this.employeeRepository.save(employee);
            }



        }catch (Exception e){

        }
    }



}
