package com.levgod.microservicioapi.services;

import com.levgod.microservicioapi.repositories.InternalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternalServiceService {

    @Autowired
    private InternalServiceRepository internalServiceRepository;

    public void addInternalService(){

    }

}
