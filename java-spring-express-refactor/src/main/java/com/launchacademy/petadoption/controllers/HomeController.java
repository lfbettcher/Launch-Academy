package com.launchacademy.petadoption.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping(value = {"/pets", "/pets/{type}", "/pets/{type}/{id}", "/adoptions/new"})
  public String forward() {
    return "forward:/";
  }
}
