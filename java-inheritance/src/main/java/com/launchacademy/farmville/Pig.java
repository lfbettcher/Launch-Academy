package com.launchacademy.farmville;

public class Pig extends Mammal {

  public Pig(boolean isAlive, int ageInYears, int milkLevelPercentage) {
    super(isAlive, ageInYears, milkLevelPercentage);
  }

  public String says() {
    return "Oink";
  }
}