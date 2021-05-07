package com.launchacademy.thymeleafForms.models;
import java.util.Date;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;

@Component
public class Trip {
  private String description;
  private Double milesTraveled;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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