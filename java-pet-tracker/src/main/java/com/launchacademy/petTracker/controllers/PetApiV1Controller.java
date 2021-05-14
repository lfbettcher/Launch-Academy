package com.launchacademy.petTracker.controllers;

import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.repositories.PetRepository;
import com.launchacademy.petTracker.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pets")
public class PetApiV1Controller {

  private final PetRepository petRepository;
  private final PetService petService;

  @Autowired
  public PetApiV1Controller(PetRepository petRepository, PetService petService) {
    this.petRepository = petRepository;
    this.petService = petService;
  }

  @GetMapping
  public Page<Pet> getPets(Pageable pageable) {
    return petRepository.findAll(pageable);
  }
}
