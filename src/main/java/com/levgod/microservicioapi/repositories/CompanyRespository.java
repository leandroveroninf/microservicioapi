package com.levgod.microservicioapi.repositories;

import com.levgod.microservicioapi.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRespository extends JpaRepository<Company, Long> {
}
