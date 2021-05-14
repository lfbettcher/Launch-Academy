package com.launchacademy.petTracker.controllers;

import com.launchacademy.petTracker.models.Species;
import com.launchacademy.petTracker.services.SpeciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/species")
public class SpeciesController {

  private final SpeciesService speciesService;

  public SpeciesController(SpeciesService speciesService) {
    this.speciesService = speciesService;
  }

  @GetMapping("/{id}")
  public String newSpecies(@PathVariable Integer id, Model model) {
    Species species = speciesService.findById(id);
    model.addAttribute("species", species);
    model.addAttribute("pets", species.getPets());
    return "species/show";
  }
}
