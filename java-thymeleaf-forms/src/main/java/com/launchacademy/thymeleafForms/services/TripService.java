package com.launchacademy.thymeleafForms.services;

import com.launchacademy.thymeleafForms.models.Trip;
import java.util.List;

public interface TripService {
  List<Trip> getList();
  void addToList(Trip trip);
}