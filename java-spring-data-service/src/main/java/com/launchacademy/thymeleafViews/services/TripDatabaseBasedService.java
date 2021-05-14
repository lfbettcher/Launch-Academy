package com.launchacademy.thymeleafViews.services;

import com.launchacademy.thymeleafViews.models.Trip;
import com.launchacademy.thymeleafViews.repositories.TripRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class TripDatabaseBasedService implements TripService {

  private TripRepository tripRepository;

  @Autowired
  public TripDatabaseBasedService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  public List<Trip> getList() {
    return (List<Trip>) tripRepository.findAll();
  }

  public void addToList(Trip trip) {
    tripRepository.save(trip);
  }
}
