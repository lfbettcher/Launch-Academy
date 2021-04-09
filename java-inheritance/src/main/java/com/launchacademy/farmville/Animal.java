package com.launchacademy.farmville;

import java.util.ArrayList;

public class Animal {

  private boolean isAlive;
  private int ageInYears;
  private boolean isSleeping;

  public Animal(boolean isAlive, int ageInYears) {
    this.isAlive = isAlive;
    this.ageInYears = ageInYears;
    this.isSleeping = false;
  }

  public String says() {
    return "something inaudible...";
  }

  public void goToSleep() {
    this.isSleeping = true;
  }

  public void wakeUp() {
    this.isSleeping = false;
  }

  public static void main(String[] args) {
    Horse blackBeauty = new Horse(true, 6, 0);
    Pig babe = new Pig(true, 4, 0);
    Cow sue = new Cow(true, 7, 100);
    Chicken foghorn = new Chicken(true, 10);

    ArrayList<Animal> animalsInBarn = new ArrayList<Animal>();
    animalsInBarn.add(blackBeauty);
    animalsInBarn.add(babe);
    animalsInBarn.add(sue);
    animalsInBarn.add(foghorn);

    for (Animal animal : animalsInBarn) {
      System.out.println(animal.says());
    }
  }
}