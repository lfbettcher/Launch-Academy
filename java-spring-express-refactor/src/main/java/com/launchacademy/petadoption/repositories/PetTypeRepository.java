package com.launchacademy.petadoption.repositories;

import com.launchacademy.petadoption.models.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Integer> {

  PetType findByType(String type);
}