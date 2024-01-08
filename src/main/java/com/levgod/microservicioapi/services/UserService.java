package com.levgod.microservicioapi.services;

import com.levgod.microservicioapi.DTO.InternalServiceDTO;
import com.levgod.microservicioapi.DTO.ServiceDTO;
import com.levgod.microservicioapi.DTO.UserDTO;
import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.entities.User;
import com.levgod.microservicioapi.repositories.DAO.UserDAO;
import com.levgod.microservicioapi.repositories.InternalServiceRepository;
import com.levgod.microservicioapi.repositories.ServicesRepository;
import com.levgod.microservicioapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    public List<User> saveAllUser(Iterable<User> users){
        try{
            return this.userDAO.saveAllUser(users);
        }catch (Exception e){
            System.out.printf("Error al crear la lista de ususario: "+e);
            return null;
        }
    }

    public UserDTO findById(Long idUser){
        try{
            return this.userDAO.findById(idUser);
        }catch (Exception e){
            System.out.println("Error al consultar un usuario: "+e);
            return null;
        }
    }

    public List<UserDTO> findAll(){
        try{
            return this.userDAO.findAll();
        }catch (Exception e){
            System.out.printf("Error al crear la lista de ususario: "+e);
            return null;
        }
    }

    public void addUserService(Long idUser, Long idService, List<Long>idInternalServices){
        try{
            this.userDAO.addUserService(idUser, idService, idInternalServices);
        }catch (Exception e){
            System.out.printf("Error al algregar un nuevo servicio al usuario: "+e);
        }
    }




}
