package com.levgod.microservicioapi.controllers;

import com.levgod.microservicioapi.entities.Employee;
import com.levgod.microservicioapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/employees")
public class EmployeeCtrl {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/company/{idCompany}")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee, @PathVariable  Long idCompany){
        try{

            return new ResponseEntity(this.employeeService.save(employee, idCompany), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/company/")
    public ResponseEntity<?> findByUser(@RequestParam  Long idCompany, @RequestParam  Long idEmployee){
        try{

            return new ResponseEntity(this.employeeService.findByUser(idEmployee, idCompany), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/company/add-service/")
    public ResponseEntity<?> addService(@RequestParam Long idEmployee, @RequestParam Long idService, @RequestParam Long idCompany){
        try{
            this.employeeService.addService(idEmployee, idService,idCompany);
            return new ResponseEntity( HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/company/add-all-service/")
    public ResponseEntity<?> addAllService(@RequestParam Long idEmployee, @RequestParam List<Long> idServices, @RequestParam Long idCompany){
        try{
            this.employeeService.addAllService(idEmployee, idServices, idCompany);
            return new ResponseEntity( HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    @PostMapping("/company/add-service-internal/")
    public ResponseEntity<?> addInternalService(@RequestParam Long idEmployee, @RequestParam Long idService, @RequestParam Long idCompany, @RequestParam Long idInternalService){
        try{
            this.employeeService.addInternalService(idEmployee, idService, idCompany, idInternalService);
            return new ResponseEntity( HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/company/add-all-service-internal/")
    public ResponseEntity<?> addAllInternalService(@RequestParam Long idEmployee, @RequestParam List<Long> idServices, @RequestParam Long idCompany, @RequestParam List<Long> idInternalServices){
        try{
            this.employeeService.addAllInternalService(idEmployee, idServices, idCompany,idInternalServices);
            return new ResponseEntity( HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
