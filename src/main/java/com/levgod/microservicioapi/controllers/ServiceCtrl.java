package com.levgod.microservicioapi.controllers;

import com.levgod.microservicioapi.entities.InternalService;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.repositories.ServicesRepository;
import com.levgod.microservicioapi.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/services")
public class ServiceCtrl {

    @Autowired
    private ServicesService servicesRepository;

    /*
        ----------------------------------------------------------------------------
                                        POST
        ----------------------------------------------------------------------------
     */

    @PostMapping
    public ResponseEntity<?> saveAll(@RequestBody Iterable<Services> entities){
        try{

            return new ResponseEntity(this.servicesRepository.saveAll(entities), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
        ----------------------------------------------------------------------------
                                        PUT
        ----------------------------------------------------------------------------
     */


    @PutMapping("/add-internal-service")
    public ResponseEntity<?> addUserService(@RequestParam Long idService, @RequestBody InternalService internalService){
        try{
            this.servicesRepository.addInternalService(idService, internalService);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/add-internal-service/all/")
    public ResponseEntity<?> addAllUserService(@RequestParam Long idService, @RequestBody Set<InternalService> internalService){
        try{
            this.servicesRepository.addAllInternalService(idService, internalService);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /*
        ----------------------------------------------------------------------------
                                        GET
        ----------------------------------------------------------------------------
     */

    @GetMapping()
    public ResponseEntity<?> findAll(){
        try{

            return new ResponseEntity(this.servicesRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/service/")
    public ResponseEntity<?> findById(@RequestParam Long idService){
        try{

            return new ResponseEntity(this.servicesRepository.findByID(idService), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
        ----------------------------------------------------------------------------
                                        DELETE
        ----------------------------------------------------------------------------
     */


}
