package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.repositories.PetTypeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetTypeService {

  private final PetTypeRepository petTypeRepository;

  @Autowired
  public PetTypeService(PetTypeRepository petTypeRepository) {
    this.petTypeRepository = petTypeRepository;
  }

  public List<PetType> findAll() {
    return (List<PetType>) petTypeRepository.findAll();
  }

  public PetType findById(Integer id) {
    Optional<PetType> petTypeOptional = this.petTypeRepository.findById(id);
    return petTypeOptional.orElse(null);
  }

  public PetType findByType(String type) {
    return this.petTypeRepository.findByType(type);
  }

  public void save(PetType petType) {
    this.petTypeRepository.save(petType);
  }
}
