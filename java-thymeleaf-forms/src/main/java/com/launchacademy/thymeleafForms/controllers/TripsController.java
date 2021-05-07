package com.launchacademy.thymeleafForms.controllers;

import com.launchacademy.thymeleafForms.models.Trip;
import com.launchacademy.thymeleafForms.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trips")
public class TripsController {

  private TripService tripService;

  @Autowired
  public TripsController(TripService tripService) {
    this.tripService = tripService;
  }

  @GetMapping("/new")
  public String getNewForm(@ModelAttribute Trip trip) {
    return "trips/new";
  }

  @PostMapping
  public String createTrip(@ModelAttribute Trip trip) {
    tripService.addToList(trip);
    return "redirect:/trips";
  }

  @GetMapping
  public String listTrips(Model model) {
    model.addAttribute("trips", tripService.getList());
    return "trips/index";
  }
}