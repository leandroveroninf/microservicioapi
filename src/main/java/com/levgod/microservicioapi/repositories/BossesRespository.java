package com.levgod.microservicioapi.repositories;

import com.levgod.microservicioapi.entities.Bosses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BossesRespository extends JpaRepository<Bosses, Long> {
}
