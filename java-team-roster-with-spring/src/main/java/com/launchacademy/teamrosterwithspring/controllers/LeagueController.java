package com.launchacademy.teamrosterwithspring.controllers;

import com.launchacademy.teamrosterwithspring.models.League;
import com.launchacademy.teamrosterwithspring.models.Team;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LeagueController {

  @GetMapping
  public String listTeams(Model model) {
    List<Team> teams = League.getLeague().getTeams();
    model.addAttribute("teams", teams);
    return "index";
  }
}
