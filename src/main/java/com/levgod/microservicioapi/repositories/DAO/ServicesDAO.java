package com.levgod.microservicioapi.repositories.DAO;

import com.levgod.microservicioapi.DTO.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.InternalServiceFindDTO;
import com.levgod.microservicioapi.DTO.ServiceFindDTO;
import com.levgod.microservicioapi.DTO.UserSimpleDTO;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.entities.User;
import com.levgod.microservicioapi.repositories.InternalServiceRepository;
import com.levgod.microservicioapi.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ServicesDAO {

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private InternalServiceRepository internalServiceRepository;

    public List<Services> saveAll(Iterable<Services> services){
        try{
             return this.servicesRepository.saveAll(services);

        }catch (Exception e){
            System.out.printf("error al guardar los servicios", e);

        }
        return null;
    }

    public List<ServiceFindDTO> findAll(){
        List<Services> servicesList = this.servicesRepository.findAll();
        List<ServiceFindDTO> serviceFindDTOS = new ArrayList<>();
        for (Services service : servicesList){

            Set<InternalServiceFindDTO> internalServicesDtos = new HashSet<>();
            Set<UserSimpleDTO> userSimpleDTOS = new HashSet<>();
            for (InternalService internalService : service.getInternalServices()){
                for(User user : internalService.getUsersInternalService()){
                    userSimpleDTOS.add(new UserSimpleDTO(user.getId(), user.getName(), user.getName(), user.getEmail(), user.getDni()));
                }
                internalServicesDtos.add(
                        new InternalServiceFindDTO(internalService.getLabel(), internalService.getIcon(), internalService.getRouterLink(), userSimpleDTOS)
                );
            }
            userSimpleDTOS = new HashSet<>();
            for(User user : service.getUsers()){
                userSimpleDTOS.add(new UserSimpleDTO(user.getId(), user.getName(), user.getName(), user.getEmail(), user.getDni()));
            }

            serviceFindDTOS.add(
                    new ServiceFindDTO(service.getLabel(), service.getIcon(), service.getRouterLink(), internalServicesDtos, userSimpleDTOS)
            );
        }

        return serviceFindDTOS;
    }

    public Services findByID(Long idService){
        try{
            return this.servicesRepository.findById(idService).orElse(null);
        }catch (Exception e){
            System.out.println("Error al buscar un servicio por su id: "+e);
            return null;
        }
    }

    public void addInternalService(Long idService, InternalService internalService){
        try{
            Services services = this.servicesRepository.findById(idService).orElse(null);
            if(services != null && internalService != null){
                internalService.setServices(services);
                services.getInternalServices().add(internalService);
                this.servicesRepository.save(services);
            }

        }catch (Exception e){
            System.out.println("Error al agregar un servicio interno: "+e);
        }

    }

    public void addAllInternalService(Long idService, Set<InternalService> internalServices){
        try{
            Services services = this.servicesRepository.findById(idService).orElse(null);
            if(services != null && internalServices != null){
                for (InternalService internalService : internalServices){
                    internalService.setServices(services);
                }

                services.getInternalServices().addAll(internalServices);
                this.servicesRepository.save(services);
            }

        }catch (Exception e){
            System.out.println("Error al agregar un servicio interno: "+e);
        }

    }

}
