package com.launchacademy.petTracker.services;

import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.repositories.PetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

  private final PetRepository petRepository;

  @Autowired
  public PetService(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  public List<Pet> findAll() {
    return (List<Pet>) this.petRepository.findAll();
  }

  public void save(Pet pet) {
    this.petRepository.save(pet);
  }
}
