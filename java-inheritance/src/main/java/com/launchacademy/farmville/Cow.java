package com.launchacademy.farmville;

public class Cow extends Mammal {

  public Cow(boolean isAlive, int ageInYears, int milkLevelPercentage) {
    super(isAlive, ageInYears, milkLevelPercentage);
  }

  @Override
  public String says() {
    return "Moo";
  }
}