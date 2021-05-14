package com.launchacademy.petTracker.seeders;

import com.google.inject.internal.util.Lists;
import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.models.Species;
import com.launchacademy.petTracker.services.PetService;
import com.launchacademy.petTracker.services.SpeciesService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetSeeder {

  private final PetService petService;
  private final SpeciesService speciesService;

  @Autowired
  public PetSeeder(PetService petService, SpeciesService speciesService) {
    this.petService = petService;
    this.speciesService = speciesService;
  }

  public void seed() {
    List<Species> allSpecies = new ArrayList<>();
    while (allSpecies.size() < 2) {
      allSpecies = speciesService.findAll();
    }

    if (Lists.newArrayList(petService.findAll()).size() == 0) {
      for (int i = 1; i <= 5; i++) {
        Pet pet = new Pet();
        pet.setName("Pet " + i);
        pet.setBreed("Breed " + i);
        pet.setAge(i);
        pet.setNeutered(i % 2 == 0);
        pet.setSpecies(allSpecies.get(i % allSpecies.size()));
        petService.save(pet);
      }
    }
  }
}