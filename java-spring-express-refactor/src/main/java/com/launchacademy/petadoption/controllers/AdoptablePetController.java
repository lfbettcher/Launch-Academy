package com.launchacademy.petadoption.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdoptablePetController {

  @GetMapping(value = {"/pets/{type}/{id}"})
  public String forward() {
    return "forward:/";
  }
}
