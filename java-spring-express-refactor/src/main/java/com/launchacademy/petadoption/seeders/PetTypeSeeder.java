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
      PetType cat = new PetType("cat", "I own you", "https://bit.ly/2Sgcd8A");
      PetType dog = new PetType("dog", "Man's best friend", "https://rb.gy/qkiiu0");
      PetType rabbit = new PetType("rabbit", "Thumper the super cool ski instructor",
          "https://rb.gy/e4cven");
      for (PetType petType : List.of(cat, dog, rabbit)) {
        this.petTypeService.save(petType);
      }
    }
  }
}
