package com.launchacademy.thymeleafForms.services;

import com.launchacademy.thymeleafForms.models.Trip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class TripSessionBasedService implements TripService {

  private List<Trip> trips;

  public TripSessionBasedService() {
    trips = new ArrayList<Trip>();
  }

  public List<Trip> getList() {
    return trips;
  }

  public void addToList(Trip trip) {
    trips.add(trip);
  }
}