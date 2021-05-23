package com.launchacademy.petadoption.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetSurrenderApplicationController {

  @GetMapping(value = {"/adoptions/new"})
  public String forward() {
    return "forward:/";
  }
}
