package com.launchacademy.thymeleafViews.controllers;

import com.launchacademy.thymeleafViews.models.Trip;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trips")
public class TripsController {

  @GetMapping
  public String findAll(Model model) {
    Trip trip = new Trip();
    trip.setDescription("Lunch meeting in Boston");
    trip.setMilesTraveled(20.1);
    trip.setTraveledOn(new Date());

    Trip otherTrip = new Trip();
    otherTrip.setDescription("Dinner meeting in Quincy");
    otherTrip.setMilesTraveled(5.8);
    otherTrip.setTraveledOn(new Date());

    List trips = new ArrayList<Trip>();
    trips.add(trip);
    trips.add(otherTrip);

    model.addAttribute("trips", trips);
    return "trips/index";
  }

}