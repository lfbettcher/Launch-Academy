package com.launchacademy.farmville;

public class Horse extends Mammal {

  public Horse(boolean isAlive, int ageInYears, int milkLevelPercentage) {
    super(isAlive, ageInYears, milkLevelPercentage);
  }

  public String says() {
    return "Neigh";
  }
}