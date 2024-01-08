package com.levgod.microservicioapi.controllers;

import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.entities.User;
import com.levgod.microservicioapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> saveAll(@RequestBody Iterable<User> entities){
        try{

            return new ResponseEntity(this.userService.saveAllUser(entities), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        try{

            return new ResponseEntity(this.userService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error al consultar todos los usuarios: "+ e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/")
    public ResponseEntity<?> findById(@RequestParam Long idUser){
        try{

            return new ResponseEntity(this.userService.findById(idUser), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error al consultar un usuario: "+ e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> addUserService(@RequestParam Long idUser,  @RequestParam Long idService, @RequestParam List<Long> idI){
        try{
            this.userService.addUserService(idUser, idService, idI);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
