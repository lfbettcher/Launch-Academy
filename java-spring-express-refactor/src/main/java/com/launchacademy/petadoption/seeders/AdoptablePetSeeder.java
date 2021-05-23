package com.launchacademy.petadoption.seeders;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.services.AdoptablePetService;
import com.launchacademy.petadoption.services.PetTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdoptablePetSeeder {

  private AdoptablePetService adoptablePetService;
  private PetTypeService petTypeService;
  private PetTypeSeeder petTypeSeeder;

  @Autowired
  public AdoptablePetSeeder(
      AdoptablePetService adoptablePetService,
      PetTypeService petTypeService,
      PetTypeSeeder petTypeSeeder) {
    this.adoptablePetService = adoptablePetService;
    this.petTypeService = petTypeService;
    this.petTypeSeeder = petTypeSeeder;
  }

  public void seed() {
    List<PetType> petTypes = this.petTypeService.findAll();
    while (petTypes.size() < 3) {
      this.petTypeSeeder.seed();
      petTypes = this.petTypeService.findAll();
    }

    if (adoptablePetService.findAll().size() == 0) {
      for (int i = 1; i <= 9; i++) {
        AdoptablePet pet = new AdoptablePet();
        pet.setName("Pet " + i);
        pet.setImgUrl("https://picsum.photos/300/" + (300 + i));
        pet.setAge(i + 1);
        pet.setVaccinationStatus(i % 2 == 0);
        pet.setAdoptionStory("Adoption Story " + i);
        pet.setAdoptionStatus(i % 3 == 0 ? "pending" : "available");
        pet.setPetType(petTypes.get(i % petTypes.size()));
        adoptablePetService.save(pet);
      }
    }
  }
}
