package com.launchacademy.petadoption.seeders;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.services.AdoptablePetService;
import com.launchacademy.petadoption.services.PetTypeService;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdoptablePetSeeder {

  private final AdoptablePetService adoptablePetService;
  private final PetTypeService petTypeService;
  private final PetTypeSeeder petTypeSeeder;

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
        pet.setAge(i + 1);
        pet.setVaccinationStatus(i % 2 == 0);
        pet.setAdoptionStatus(i % 3 == 0 ? "pending" : "available");
        PetType type = petTypes.get(i % petTypes.size());
        pet.setPetType(type);
        pet.setAdoptionStory(getHipsterIpsum());
        int petNum = (i - 1) / 3 + 1;
        if (type.getType().equals("cat")) {
          pet.setName("Cat " + petNum);
          pet.setImgUrl("https://placekitten.com/" + (298 + i));
        } else if (type.getType().equals("dog")) {
          pet.setName("Dog " + petNum);
          pet.setImgUrl("https://placedog.net/" + (298 + i));
        } else {
          pet.setName("Bear " + petNum);
          pet.setImgUrl("https://placebear.com/300/" + (298 + i));
        }
        adoptablePetService.save(pet);
      }
    }
  }

  private String getHipsterIpsum() {
    Random rand = new Random();
    int sentences = rand.nextInt(4 - 1) + 1;
    String command = "curl -s https://hipsum.co/api/?type=hipster-centric&sentences=" + sentences;
    String adoptionStory;
    try {
      Process process = Runtime.getRuntime().exec(command);
      InputStream inputStream = process.getInputStream();
      String s = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      adoptionStory = s.substring(2, s.length() - 2);
      process.destroy();
    } catch (Exception e) {
      adoptionStory = "Story " + sentences;
    }
    return adoptionStory;
  }
}
