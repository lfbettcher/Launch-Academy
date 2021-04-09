package com.launchacademy.farmville;

public class Mammal extends Animal {

  public static final int DEFAULT_MILKING_PERCENTAGE = 5;
  private int milkLevelPercentage;

  public Mammal(boolean isAlive, int ageInYears, int milkLevelPercentage) {
    super(isAlive, ageInYears);
    this.milkLevelPercentage = milkLevelPercentage;
  }

  public void milk() {
    this.milk(DEFAULT_MILKING_PERCENTAGE);
  }

  public void milk(int percentageToMilk) {
    if (this.milkLevelPercentage - percentageToMilk > 0) {
      this.milkLevelPercentage -= percentageToMilk;
    } else {
      this.milkLevelPercentage = 0;
    }
  }
}