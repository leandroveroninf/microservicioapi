package com.levgod.microservicioapi.repositories.DAO;

import com.levgod.microservicioapi.DTO.internalServices.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.services.ServiceConvert;
import com.levgod.microservicioapi.DTO.services.ServiceDTO;
import com.levgod.microservicioapi.DTO.services.ServiceFindDTO;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.repositories.InternalServiceRepository;
import com.levgod.microservicioapi.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Agregamos los servicios y los servicios internos al servicio
 * Consultamos los servicios cons todos los servicios internos y las companias que tienen asiociadas
 */
@Repository
public class ServicesDAO {

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private InternalServiceRepository internalServiceRepository;

    @Autowired
    private ServiceConvert serviceConvert;

    public  Set<ServiceDTO> saveAll(Iterable<Services> services){
        try{
            Set<ServiceDTO> servicesDTO = new HashSet<>();
            List<Services> rs = this.servicesRepository.saveAll(services);
             for(Services servicesResult : rs){
                 servicesDTO.add(this.serviceConvert.convertDTO(servicesResult));
             }

             return servicesDTO;
        }catch (Exception e){
            System.out.print("error al guardar los servicios: "+e);

        }
        return null;
    }

    public Services save(Services services){
        try {
            return this.servicesRepository.save(services);

        }catch (Exception e){

            return null;
        }
    }


    public Set<ServiceDTO> findAll(){
        List<Services> servicesList = this.servicesRepository.findAll();
        return new HashSet<>(
                servicesList.stream()
                        .map(services -> this.serviceConvert.convertDTO(services)).toList()
        );
    }

    public ServiceDTO findByID(Long idService){
        try{
            Services services = this.servicesRepository.findById(idService).orElse(null);

            if(services != null){
                return this.serviceConvert.convertDTO(services);
            }

            return null;

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
