package com.launchacademy.petTracker.services;

import com.launchacademy.petTracker.dtos.PetDto;
import com.launchacademy.petTracker.mappers.PetMapper;
import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.repositories.PetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

  private final PetRepository petRepository;
  private final PetMapper petMapper;

  @Autowired
  public PetService(PetRepository petRepository, PetMapper petMapper) {
    this.petRepository = petRepository;
    this.petMapper = petMapper;
  }

  public List<Pet> findAll() {
    return (List<Pet>) this.petRepository.findAll();
  }

  public void saveDto(PetDto dto) {
    Pet pet = this.petMapper.petDtoToPet(dto);
//    Pet pet = PetDto.dtoToPet(dto);
    this.savePet(pet);
  }

  public void savePet(Pet pet) {
    this.petRepository.save(pet);
  }

  public void save(Pet pet) {
    PetDto dto = new PetDto();
    dto.setSpecies();
  }
}
