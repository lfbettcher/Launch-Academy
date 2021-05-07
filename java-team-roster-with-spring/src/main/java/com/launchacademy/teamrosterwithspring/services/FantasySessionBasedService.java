package com.launchacademy.teamrosterwithspring.services;

import com.launchacademy.teamrosterwithspring.models.Team;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class FantasySessionBasedService implements FantasyService {

  private List<Team> teams;

  public FantasySessionBasedService() {
    this.teams = new ArrayList<Team>();
  }

  public List<Team> findAll() {
    return this.teams;
  }

  public void addToList(Team team) {
    this.teams.add(team);
  }

  public Team get(Integer id) {
    return this.teams.get(id);
  }
}
