package com.levgod.microservicioapi.repositories;

import com.levgod.microservicioapi.entities.InternalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalServiceRepository extends JpaRepository<InternalService, Long> {
}
