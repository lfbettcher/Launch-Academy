package com.launchacademy.donutTracker.services;

import com.launchacademy.donutTracker.repositories.DonutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonutService {

  private final DonutRepository donutRepository;

  @Autowired
  public DonutService(DonutRepository donutRepository) {
    this.donutRepository = donutRepository;
  }
}
