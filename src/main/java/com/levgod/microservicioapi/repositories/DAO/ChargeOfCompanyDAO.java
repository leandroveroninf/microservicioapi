package com.levgod.microservicioapi.repositories.DAO;

import com.levgod.microservicioapi.repositories.ChargeOfCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChargeOfCompanyDAO {

    @Autowired
    private ChargeOfCompanyRepository chargeOfCompanyRepository;

}
