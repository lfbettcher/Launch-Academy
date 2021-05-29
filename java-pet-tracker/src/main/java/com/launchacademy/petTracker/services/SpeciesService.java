package com.launchacademy.petTracker.services;

import com.launchacademy.petTracker.models.Species;
import com.launchacademy.petTracker.repositories.SpeciesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeciesService {

  private final SpeciesRepository speciesRepository;

  @Autowired
  public SpeciesService(SpeciesRepository speciesRepository) {
    this.speciesRepository = speciesRepository;
  }

  public List<Species> findAll() {
    return (List<Species>) this.speciesRepository.findAll();
  }

  public Species findById(Integer id) {
    Optional<Species> speciesOptional = this.speciesRepository.findById(id);
    return speciesOptional.orElse(null);
  }

  public Species findByName(String name) {
    Optional<Species> speciesOptional = this.speciesRepository.findByName(name);
    return speciesOptional.orElse(null);
  }

  public void save(Species species) {
    this.speciesRepository.save(species);
  }
}