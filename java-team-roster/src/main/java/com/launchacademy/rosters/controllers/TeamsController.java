package com.launchacademy.rosters.controllers;

import com.launchacademy.rosters.models.League;
import com.launchacademy.rosters.models.Player;
import com.launchacademy.rosters.models.Team;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/", "/team"})
public class TeamsController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    League league = League.getLeague();
    List<Team> teams = league.getTeams();
    req.setAttribute("teams", teams);

    String teamIndex = req.getParameter("teamIndex");
    if (teamIndex != null) {
      try {
        int index = Integer.parseInt(teamIndex);
        Team team = teams.get(index);
        req.setAttribute("team", team);
        List<Player> players = team.getPlayers();
        req.setAttribute("players", players);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/teams/show.jsp");
        dispatcher.forward(req, resp);
      } catch (NumberFormatException | IndexOutOfBoundsException e) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
    } else {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/teams/index.jsp");
      dispatcher.forward(req, resp);
    }
  }
}
