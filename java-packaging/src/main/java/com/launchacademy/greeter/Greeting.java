package com.launchacademy.greeter;

public class Greeting {
  private String name;

  public Greeting(String name) {
    this.name = name;
  }

  public String sayHi() {
    return "Hi " + name;
  }

}
