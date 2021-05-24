package com.launchacademy.petadoption.controllers;

import com.launchacademy.petadoption.models.PetSurrenderApplication;
import com.launchacademy.petadoption.services.PetSurrenderApplicationService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/surrender")
public class SurrenderApiV1Controller {

  private final PetSurrenderApplicationService petSurrenderApplicationService;

  @Autowired
  public SurrenderApiV1Controller(PetSurrenderApplicationService petSurrenderApplicationService) {
    this.petSurrenderApplicationService = petSurrenderApplicationService;
  }

  @PostMapping
  public ResponseEntity createPetSurrenderApplication(
      @Valid @RequestBody PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<List>(bindingResult.getAllErrors(),
          HttpStatus.UNPROCESSABLE_ENTITY);
    } else {
      return new ResponseEntity<PetSurrenderApplication>(
          petSurrenderApplicationService.save(petSurrenderApplication), HttpStatus.CREATED);
    }
  }
}
