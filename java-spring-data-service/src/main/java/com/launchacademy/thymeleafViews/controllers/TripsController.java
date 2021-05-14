package com.launchacademy.thymeleafViews.controllers;

import com.launchacademy.thymeleafViews.models.Trip;
import com.launchacademy.thymeleafViews.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TripsController {

  private TripService tripService;

  @Autowired
  public TripsController(TripService tripService) {
    this.tripService = tripService;
  }


  @GetMapping("/trips")
  public String listTrips(Model model) {
    model.addAttribute("trips", tripService.getList());
    return "trips/index";
  }

  @GetMapping("/new")
  public String getNewForm(@ModelAttribute Trip trip) {
    System.out.println("in new");
    return "trips/new";
  }

//	@PostMapping
//	public String createTrip(@ModelAttribute Trip trip) {
//		System.out.println(trip);
//		return "trips/show";
//	}

  @PostMapping
  public String createTrip(@ModelAttribute Trip trip) {
    tripService.addToList(trip);
    return "redirect:/trips";
  }
}
