package com.levgod.microservicioapi.repositories.DAO;

import com.levgod.microservicioapi.DTO.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.ServiceDTO;
import com.levgod.microservicioapi.DTO.UserDTO;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.entities.User;
import com.levgod.microservicioapi.repositories.InternalServiceRepository;
import com.levgod.microservicioapi.repositories.ServicesRepository;
import com.levgod.microservicioapi.repositories.UserRepository;
import com.levgod.microservicioapi.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private InternalServiceRepository internalServiceRepository;

    private Set<ServiceDTO> innerJoinMyServiceAndMyIneterServices(Set<Services> services, Set<InternalService> internalServices){

        Long idS = 0L;
        Long idIS = 0L;

        Set<ServiceDTO> myServices = new HashSet<>();

        for(Services service : services){
            idS = service.getId();
            ServiceDTO myService = null;
            Set<InternalServiceDTO> internalServicesDtos = new HashSet<>();
            for(InternalService internalService : internalServices){

                service.getInternalServices().forEach(IS -> {
                    if(IS.getId() == internalService.getId()){
                        internalServicesDtos
                                .add(
                                        new InternalServiceDTO(internalService.getLabel(), internalService.getIcon(), internalService.getRouterLink()
                                        )
                                );
                    }
                });
            }

            myServices.add(new ServiceDTO(service.getLabel(), service.getIcon(), service.getRouterLink(), internalServicesDtos));

        }

        return myServices;

    }

    public List<User> saveAllUser(Iterable<User> users){
        try{
            return this.userRepository.saveAll(users);
        }catch (Exception e){
            System.out.printf("Error al crear la lista de ususario: "+e);
            return null;
        }
    }

    public UserDTO findById(Long idUser){
        try{
            User user = this.userRepository.findById(idUser).orElse(null);
            if(user != null){
                Set<Services> services = user.getMyServices();
                Set<ServiceDTO> myServices = this.innerJoinMyServiceAndMyIneterServices(services, user.getMyServicesInternal());
                Set<ServiceDTO> totalService = new HashSet<>();

                // Obtengo los servicios y los servicios internos contratados
                for (Services service : services) {

                    Set<InternalServiceDTO> internalServiceDTOS = new HashSet<>();

                    internalServiceDTOS = new HashSet<>();

                    // Obtengo en todal de los servicios contratados y todos los servicios internos
                    for (InternalService internalService : service.getInternalServices()){
                        internalServiceDTOS.add(
                                new InternalServiceDTO(internalService.getLabel(), internalService.getIcon(), internalService.getRouterLink())
                        );
                    }

                    totalService.add(new ServiceDTO(service.getLabel(), service.getIcon(), service.getRouterLink(), internalServiceDTOS));

                }



                return new UserDTO(user.getName(), user.getLastName(), user.getEmail(), user.getDni(), myServices, totalService);

            }
        }catch (Exception e){
            System.out.println("Error al consultar un usuario: "+e);
            return null;
        }
        return null;
    }

    public List<UserDTO> findAll(){
        try{
            List<User> userList = this.userRepository.findAll();
            List<UserDTO> userDTOList = new ArrayList<>();

            for (User user : userList){
                if(user != null){
                    Set<Services> services = user.getMyServices();
                    Set<ServiceDTO> myServices = this.innerJoinMyServiceAndMyIneterServices(services, user.getMyServicesInternal());
                    Set<ServiceDTO> totalService = new HashSet<>();

                    // Obtengo los servicios y los servicios internos contratados
                    for (Services service : services) {

                        Set<InternalServiceDTO> internalServiceDTOS = new HashSet<>();

                        internalServiceDTOS = new HashSet<>();

                        // Obtengo en todal de los servicios contratados y todos los servicios internos
                        for (InternalService internalService : service.getInternalServices()){
                            internalServiceDTOS.add(
                                    new InternalServiceDTO(internalService.getLabel(), internalService.getIcon(), internalService.getRouterLink())
                            );
                        }

                        totalService.add(new ServiceDTO(service.getLabel(), service.getIcon(), service.getRouterLink(), internalServiceDTOS));

                    }

                    userDTOList.add(new UserDTO(user.getName(), user.getLastName(), user.getEmail(), user.getDni(), myServices, totalService));


                }

            }

            return userDTOList;
        }catch (Exception e){
            System.out.printf("Error al crear la lista de ususario: "+e);
            return null;
        }
    }

    public void addUserService(Long idUser, Long idService, List<Long>idInternalServices){
        try{
            User user = this.userRepository.findById(idUser).orElse(null);
            Services services = this.servicesService.findByID(idService);

            // comprobamos si existe el usuario y el servicio
            if(user != null && services != null){
                Set<InternalService> intServicesSet = user.getMyServicesInternal();
                for (Long idIntS : idInternalServices){

                    InternalService internalService = this.internalServiceRepository.findById(idIntS).orElse(null);

                    if(internalService != null){
                        Set<User> userAdd = new HashSet<>();
                        userAdd.add(user);
                        internalService.setUsersInternalService(userAdd);
                        intServicesSet.add(internalService);
                    }


                }

                user.setMyServicesInternal(intServicesSet);
                user.getMyServices().add(services);
                services.getUsers().add(user);

                this.userRepository.save(user);
                this.servicesRepository.save(services);

            }

        }catch (Exception e){
            System.out.printf("Error al algregar un nuevo servicio al usuario: "+e);
        }
    }

}
