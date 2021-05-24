package com.launchacademy.petadoption.controllers;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.services.AdoptablePetService;
import com.launchacademy.petadoption.services.PetTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/pets")
public class PetsApiV1Controller {

  private final PetTypeService petTypeService;
  private final AdoptablePetService adoptablePetService;

  @Autowired
  public PetsApiV1Controller(PetTypeService petTypeService,
      AdoptablePetService adoptablePetService) {
    this.petTypeService = petTypeService;
    this.adoptablePetService = adoptablePetService;
  }

  @GetMapping
  public List<PetType> getPetTypes() {
    return petTypeService.findAll();
  }

  @GetMapping("/{type}")
  public List<AdoptablePet> getPetsOfType(@PathVariable String type) {
    return petTypeService.findByType(type).getAdoptablePets();
  }

  @GetMapping("/{type}/{id}")
  public AdoptablePet getAdoptablePet(@PathVariable String type, @PathVariable Integer id) {
    return adoptablePetService.findByIdMatchType(id, type);
  }
}
