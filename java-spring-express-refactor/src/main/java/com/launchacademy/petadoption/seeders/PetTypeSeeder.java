package com.launchacademy.petadoption.seeders;

import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.services.PetTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetTypeSeeder {

  private PetTypeService petTypeService;

  @Autowired
  public PetTypeSeeder(PetTypeService petTypeService) {
    this.petTypeService = petTypeService;
  }

  public void seed() {
    if (this.petTypeService.findAll().size() == 0) {
      PetType cat = new PetType("cat", "I own you", "https://placekitten.com/298");
      PetType dog = new PetType("dog", "Man's best friend", "https://placedog.net/298");
      PetType bear = new PetType("bear", "Mmm, these bearies are so sweet!",
          "https://placebear.com/300/298");
      for (PetType petType : List.of(cat, dog, bear)) {
        this.petTypeService.save(petType);
      }
    }
  }
}
