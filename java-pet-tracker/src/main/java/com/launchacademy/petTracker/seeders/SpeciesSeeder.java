package com.launchacademy.petTracker.seeders;

import com.google.inject.internal.util.Lists;
import com.launchacademy.petTracker.models.Species;
import com.launchacademy.petTracker.services.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpeciesSeeder {

  private final SpeciesService speciesService;

  @Autowired
  public SpeciesSeeder(SpeciesService speciesService) {
    this.speciesService = speciesService;
  }

  public void seed() {
    Species species1 = new Species();
    Species species2 = new Species();

    if (Lists.newArrayList(speciesService.findAll()).size() == 0) {
      species1.setName("Species 1");
      speciesService.save(species1);
      species2.setName("Species 2");
      speciesService.save(species2);
    }
  }
}