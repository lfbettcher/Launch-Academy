package com.launchacademy.thymeleafViews.models;

import java.util.Date;

public class Trip {

  private String description;
  private Double milesTraveled;
  private Date traveledOn;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getMilesTraveled() {
    return milesTraveled;
  }

  public void setMilesTraveled(Double milesTraveled) {
    this.milesTraveled = milesTraveled;
  }

  public Date getTraveledOn() {
    return traveledOn;
  }

  public void setTraveledOn(Date traveledOn) {
    this.traveledOn = traveledOn;
  }
}