package com.launchacademy.spaceMonkey.models;

public class Viewscreen {
  private Integer id;
  private String name;
  private String role;
  private String location;
  private int bananas;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getBananas() {
    return bananas;
  }

  public void setBananas(int bananas) {
    this.bananas = bananas;
  }
}
