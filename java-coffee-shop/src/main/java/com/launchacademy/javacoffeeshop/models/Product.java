package com.launchacademy.javacoffeeshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class Product {

  private Integer id;
  private String name;
  private String description;
  private Double price;
  private Integer caffeineRating;
}
