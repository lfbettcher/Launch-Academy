package com.launchacademy.teamrosterwithspring.controllers;

import com.launchacademy.teamrosterwithspring.models.Team;
import com.launchacademy.teamrosterwithspring.services.FantasyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fantasy/teams")
public class FantasyController {

  private FantasyService fantasyService;

  @GetMapping
  public String getFantasyLeague() {
    return "fantasy/index";
  }
  @GetMapping("/new")
  public String fantasyForm(@ModelAttribute Team team) {
    return "fantasy/new";
  }

  @PostMapping("/fantasy/teams")
  public String createFantasyTeam(@ModelAttribute Team team) {
    fantasyService.addToList(team);
    return "redirect:/fantasy/teams";
  }
}
