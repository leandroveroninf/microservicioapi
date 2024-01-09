package com.levgod.microservicioapi.services;

import com.levgod.microservicioapi.DTO.services.ServiceDTO;
import com.levgod.microservicioapi.DTO.services.ServiceFindDTO;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.repositories.DAO.ServicesDAO;
import com.levgod.microservicioapi.repositories.InternalServiceRepository;
import com.levgod.microservicioapi.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ServicesDAO servicesDAO;

    @Autowired
    private InternalServiceRepository internalServiceRepository;

    public Set<ServiceDTO> saveAll(Iterable<Services> services){
        try{
            return this.servicesDAO.saveAll(services);
        }catch (Exception e){
            System.out.printf("error al guardar los servicios", e);

        }
        return null;
    }

    public Set<ServiceDTO> findAll(){
        return this.servicesDAO.findAll();
    }

    public ServiceDTO findByID(Long idService){
        try{
            return this.servicesDAO.findByID(idService);
        }catch (Exception e){
            System.out.println("Error al buscar un servicio por su id: "+e);
            return null;
        }
    }

    public void addInternalService(Long idService, InternalService internalService){
        try{
            this.servicesDAO.addInternalService(idService, internalService);
        }catch (Exception e){
            System.out.println("Error al agregar un servicio interno: "+e);
        }

    }

    public void addAllInternalService(Long idService, Set<InternalService> internalServices){
        try{
            this.servicesDAO.addAllInternalService(idService, internalServices);
        }catch (Exception e){
            System.out.println("Error al agregar un servicio interno: "+e);
        }

    }

}
