package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.repositories.AdoptablePetRepository;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class AdoptablePetService {

  private final AdoptablePetRepository adoptablePetRepository;

  @Autowired
  public AdoptablePetService(AdoptablePetRepository adoptablePetRepository) {
    this.adoptablePetRepository = adoptablePetRepository;
  }

  public List<AdoptablePet> findAll() {
    return (List<AdoptablePet>) adoptablePetRepository.findAll();
  }

  public AdoptablePet findById(Integer id) {
    return adoptablePetRepository.findById(id).orElseThrow(UrlNotFoundException::new);
  }

  public AdoptablePet findByIdMatchType(Integer id, String type) {
    AdoptablePet pet = findById(id);
    PetType petType = pet.getPetType();
    if (petType.getType().equals(type)) {
      return pet;
    }
    throw new UrlNotFoundException();
  }

  public void save(AdoptablePet pet) {
    adoptablePetRepository.save(pet);
  }

  @NoArgsConstructor
  private class UrlNotFoundException extends RuntimeException {

  }

  @ControllerAdvice
  private class UrlNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(UrlNotFoundException ex) {
      return ex.getMessage();
    }
  }
}
