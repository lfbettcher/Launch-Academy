package com.launchacademy.teamrosterwithspring.controllers;

import com.launchacademy.teamrosterwithspring.models.League;
import com.launchacademy.teamrosterwithspring.models.Team;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teams")
public class TeamsController {

  @GetMapping("/{id}")
  public String showTeam(@PathVariable int id, Model model) {
    List<Team> teams = League.getLeague().getTeams();
    if (id < 0 || id >= teams.size()) {
      return "teams/notFound";
//      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid id");
    }
    Team team = teams.get(id);
    model.addAttribute("team", team);
    return "teams/show";
  }
}
