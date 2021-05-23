package com.launchacademy.petadoption.repositories;

import com.launchacademy.petadoption.models.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Integer> {

  //  @Query("SELECT t FROM PetType t WHERE t.type = ?1")
//  Optional<PetType> findByType(String type);
  PetType findByType(String type);
}