package com.launchacademy.petTracker.controllers;

import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.repositories.PetRepository;
import com.launchacademy.petTracker.services.PetService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pets")
public class PetController {

  private final PetRepository petRepository;
  private final PetService petService;

  @Autowired
  public PetController(PetRepository petRepository, PetService petService) {
    this.petRepository = petRepository;
    this.petService = petService;
  }

  @GetMapping
  public String getPets(Pageable pageable, Model model) {
    model.addAttribute("pets", petRepository.findAll(pageable));
    return "pets/index";
  }

  @GetMapping("/new")
  public String newPet(@ModelAttribute Pet pet) {
    return "pets/new";
  }

  @PostMapping("/new")
  public String addPet(@ModelAttribute @Valid Pet pet, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "pets/new";
    } else {
//      petService.saveDto(dto);
      petService.save(pet);
      return "redirect:/pets";
    }
  }
}
