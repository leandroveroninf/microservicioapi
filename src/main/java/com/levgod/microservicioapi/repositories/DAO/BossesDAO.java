package com.levgod.microservicioapi.repositories.DAO;

import com.levgod.microservicioapi.entities.Bosses;
import com.levgod.microservicioapi.entities.Company;
import com.levgod.microservicioapi.entities.Services;
import com.levgod.microservicioapi.repositories.BossesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class BossesDAO {

    @Autowired
    private BossesRespository bossesRespository;

    private static final Logger logger = Logger.getLogger(BossesDAO.class.getName());

    public Bosses saveBosses(Bosses bosse){
        try{
            return this.bossesRespository.save(bosse);
        }catch (Exception e){
            logger.warning("Error al guardar el jefe: "+e.getMessage());
            return null;
        }
    }

    public List<Bosses> saveAllBosses(Iterable<Bosses> bosses){
        try{
            return this.bossesRespository.saveAll(bosses);
        }catch (Exception e){
            logger.warning("Error al guardar el jefe: "+e.getMessage());
            return null;
        }
    }


    public Bosses findByIdBosses(Long id){
        try{
            return this.bossesRespository.findById(id).orElse(null);
        }catch (Exception e){

            return null;
        }
    }

    public List<Bosses> findAllBosses(){
        try{
            return this.bossesRespository.findAll();
        }catch (Exception e){

            return null;
        }
    }


    public void addServiceBosses(Company company, Services services, Bosses bosses){


    }



}
