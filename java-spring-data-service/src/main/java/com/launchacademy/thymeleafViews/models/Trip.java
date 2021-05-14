package com.launchacademy.thymeleafViews.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "trips")
public class Trip {

  @Id
  @SequenceGenerator(name = "trips_generator",
      sequenceName = "trips_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "trips_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

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
