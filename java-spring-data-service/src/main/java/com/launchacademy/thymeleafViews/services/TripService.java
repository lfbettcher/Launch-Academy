package com.launchacademy.thymeleafViews.services;

import com.launchacademy.thymeleafViews.models.Trip;
import java.util.List;

public interface TripService {

  List<Trip> getList();

  void addToList(Trip trip);
}
