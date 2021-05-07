package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.services.StarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/starships")
public class StarShipsController {

  @Autowired
  StarShipService service;

  @GetMapping
  public String listStarships(Model model) {
    model.addAttribute("starships", service.findAll());
    return "starships/index";
  }

  @GetMapping("/{id}")
  public String showStarship(@PathVariable int id, Model model) {
    model.addAttribute("starship", service.get(id));
    return "starships/show";
  }
}