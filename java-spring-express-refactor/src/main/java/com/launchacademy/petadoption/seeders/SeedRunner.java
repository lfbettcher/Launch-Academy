package com.launchacademy.petadoption.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedRunner implements CommandLineRunner {

  private final PetTypeSeeder petTypeSeeder;
  private final AdoptablePetSeeder adoptablePetSeeder;

  @Autowired
  public SeedRunner(PetTypeSeeder petTypeSeeder,
      AdoptablePetSeeder adoptablePetSeeder) {
    this.petTypeSeeder = petTypeSeeder;
    this.adoptablePetSeeder = adoptablePetSeeder;
  }

  @Override
  public void run(String... args) throws Exception {
    petTypeSeeder.seed();
    adoptablePetSeeder.seed();
  }
}