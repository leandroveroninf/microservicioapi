package com.levgod.microservicioapi.DTO;

public interface Convert <D, E>{
    D convertDTO(E entity);
    E convertToEntity(D dto);
}
