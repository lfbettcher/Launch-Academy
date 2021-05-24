package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.PetSurrenderApplication;
import com.launchacademy.petadoption.repositories.PetSurrenderApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetSurrenderApplicationService {

  private final PetSurrenderApplicationRepository petSurrenderApplicationRepository;
  private final PetTypeService petTypeService;

  @Autowired
  public PetSurrenderApplicationService(
      PetSurrenderApplicationRepository petSurrenderApplicationRepository,
      PetTypeService petTypeService) {
    this.petSurrenderApplicationRepository = petSurrenderApplicationRepository;
    this.petTypeService = petTypeService;
  }

  public PetSurrenderApplication save(PetSurrenderApplication petSurrenderApplication) {
    petSurrenderApplication
        .setPetType(petTypeService.findByType(petSurrenderApplication.getPetType().getType()));
    petSurrenderApplicationRepository.save(petSurrenderApplication);
    return petSurrenderApplication;
  }
}
