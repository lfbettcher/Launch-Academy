package com.launchacademy.thymeleafViews.repositories;

import com.launchacademy.thymeleafViews.models.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Integer> {

}
