package com.launchacademy.petTracker.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

  private final SpeciesSeeder speciesSeeder;
  private final PetSeeder petSeeder;

  @Autowired
  public Seeder(SpeciesSeeder speciesSeeder, PetSeeder petSeeder) {
    this.speciesSeeder = speciesSeeder;
    this.petSeeder = petSeeder;
  }

  @Override
  public void run(String... args) throws Exception {
    speciesSeeder.seed();
    petSeeder.seed();
  }
}