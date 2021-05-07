package com.launchacademy.teamrosterwithspring.services;

import com.launchacademy.teamrosterwithspring.models.Team;
import java.util.List;

public interface FantasyService {

  List<Team> findAll();
  void addToList(Team team);
}
