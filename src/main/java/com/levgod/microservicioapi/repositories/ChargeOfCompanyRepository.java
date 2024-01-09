package com.levgod.microservicioapi.repositories;

import com.levgod.microservicioapi.entities.ChargeOfCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeOfCompanyRepository extends JpaRepository<ChargeOfCompany, Long> {
}
