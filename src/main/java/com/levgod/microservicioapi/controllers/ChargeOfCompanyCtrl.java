package com.levgod.microservicioapi.controllers;

import com.levgod.microservicioapi.services.ChargeOfCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/charge-of-company")
public class ChargeOfCompanyCtrl {

    @Autowired
    private ChargeOfCompanyService companyService;


    @PostMapping("/company/add-service/")
    public ResponseEntity<?> addService(@RequestParam Long idOfCompany, @RequestParam Long idService, @RequestParam Long idCompany) {
        try {
            this.companyService.addService(idCompany, idService, idOfCompany);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/company/add-all-service/")
    public ResponseEntity<?> addAllService(@RequestParam Long idOfCompany, @RequestParam List<Long> idServices, @RequestParam Long idCompany) {
        try {
            this.companyService.addAllService(idOfCompany, idServices, idCompany);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/company/add-service-internal/")
    public ResponseEntity<?> addInternalService(@RequestParam Long idOfCompany, @RequestParam Long idService, @RequestParam Long idCompany, @RequestParam Long idInternalService) {
        try {
            this.companyService.addInternalService(idOfCompany, idService, idCompany, idInternalService);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/company/add-all-service-internal/")
    public ResponseEntity<?> addAllInternalService(@RequestParam Long idOfCompany, @RequestParam List<Long> idServices, @RequestParam Long idCompany, @RequestParam List<Long> idInternalServices) {
        try {
            this.companyService.addAllInternalService(idOfCompany, idServices, idCompany, idInternalServices);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
