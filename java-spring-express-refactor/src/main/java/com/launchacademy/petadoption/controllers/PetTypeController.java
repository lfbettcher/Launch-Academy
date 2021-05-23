package com.launchacademy.petadoption.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetTypeController {

  @GetMapping(value = {"/pets/{type}"})
  public String forward() {
    return "forward:/";
  }
}
