package com.launchacademy.partyplanner.controllers;

import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.repositories.PartyRepository;
import com.launchacademy.partyplanner.services.PartyService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PartiesController {

  private final PartyRepository partyRepository;
  private final PartyService partyService;

  @Autowired
  public PartiesController(PartyRepository partyRepository, PartyService partyService) {
    this.partyRepository = partyRepository;
    this.partyService = partyService;
  }

  @GetMapping
  public String redirectRoot() {
    return "redirect:/parties";
  }

  @GetMapping("/parties")
  public String getParties(Pageable pageable, Model model) {
    model.addAttribute("parties", partyRepository.findAll(pageable));
    return "parties/index";
  }

  @GetMapping("/parties/{id}")
  public String newParty(@PathVariable Integer id, Model model) {
    model.addAttribute("party", partyService.findById(id));
    return "parties/show";
  }

  @GetMapping("/parties/new")
  public String newParty(@ModelAttribute Party party) {
    return "parties/new";
  }

  @PostMapping
  public String addParty(@ModelAttribute @Valid Party party, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "parties/new";
    } else {
      partyRepository.save(party);
      return "redirect:/parties";
    }
  }
}
