package com.levgod.microservicioapi.controllers;

import com.levgod.microservicioapi.entities.Bosses;
import com.levgod.microservicioapi.entities.ChargeOfCompany;
import com.levgod.microservicioapi.entities.Company;
import com.levgod.microservicioapi.entities.Employee;
import com.levgod.microservicioapi.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/companies")
public class CompanyCtrl {

    @Autowired
    private CompanyService companyService;


    /*
        Agregamos la compania
     */

    @PostMapping
    public ResponseEntity<?> saveCompany(@RequestBody Company company){
        try{

            return new ResponseEntity(this.companyService.saveCompany(company), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
       Consultamo una compania
    */
    @GetMapping("/id/{idCompany}")
    public ResponseEntity<?> findByIdCompany(@PathVariable  Long idCompany){
        try{

            return new ResponseEntity(this.companyService.findByIdCompany(idCompany), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
       Agregamos todas compania
    */
    @GetMapping()
    public ResponseEntity<?> findAllCompany(){
        try{

            return new ResponseEntity(this.companyService.findAllCompany(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


     /*
        Agregamos servicios
     */

    @PutMapping("/company/add-service/")
    public ResponseEntity<?> addService(@RequestParam Long idCompany, @RequestParam Long idService ){
        try{
            this.companyService.addService(idCompany, idService);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/company/add-service-all/")
    public ResponseEntity<?> addAllService(@RequestParam Long idCompany, @RequestParam List<Long> idServices ){
        try{
            this.companyService.addAllService(idCompany, idServices);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/company/add-internal-service/")
    public ResponseEntity<?> addInternalService(@RequestParam Long idCompany, @RequestParam Long idInternalService ){
        try{
            this.companyService.addInternalService(idCompany,idInternalService);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/company/add-internal-service-all/")
    public ResponseEntity<?> addAllInternalService(@RequestParam Long idCompany, @RequestParam List<Long> idInternalServices ){
        try{
            this.companyService.addAllInternalService(idCompany,idInternalServices);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
       Agregamos los fejes
    */
    @PutMapping("/company/add-boss/")
    public ResponseEntity<?> addBoss(@RequestParam Long idCompany, @RequestBody Bosses boss ){
        try{
            this.companyService.addBossse(idCompany, boss);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/company/add-boss-all/")
    public ResponseEntity<?> addAllBoss(@RequestParam Long idCompany, @RequestBody Set<Bosses> bosses ){
        try{
            this.companyService.addAllBosses(idCompany, bosses);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
       Agregamos los encargados de la compania
    */
    @PutMapping("/company/charge-of-company/")
    public ResponseEntity<?> addChargeOfCompany(@RequestParam Long idCompany, @RequestBody ChargeOfCompany ofCompany ){
        try{
            this.companyService.addChargeOfCompany(idCompany, ofCompany);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/company/charge-of-company-all/")
    public ResponseEntity<?> addAllChargeOfCompany(@RequestParam Long idCompany, @RequestBody Set<ChargeOfCompany> ofCompany ){
        try{
            this.companyService.addAllChargeOfCompany(idCompany, ofCompany);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
        Agregamos los empleados de la compania
    */
    @PutMapping("/company/employee/")
    public ResponseEntity<?> addEmployee(@RequestParam Long idCompany, @RequestBody Employee employee ){
        try{
            this.companyService.addEmployee(idCompany, employee);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/company/employee-all/")
    public ResponseEntity<?> addAllEmployee(@RequestParam Long idCompany, @RequestBody Set<Employee> employees ){
        try{
            this.companyService.addAllEmployee(idCompany, employees);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
