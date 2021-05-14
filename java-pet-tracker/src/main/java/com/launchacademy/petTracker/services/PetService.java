package com.launchacademy.petTracker.services;

import com.launchacademy.petTracker.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

  private final PetRepository petRepository;

  @Autowired
  public PetService(PetRepository petRepository) {
    this.petRepository = petRepository;
  }
}
