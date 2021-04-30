package com.launchacademy.greetings;

public class Greeting {
  private String firstName;
  private String lastName;
  private String language;
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getLanguage() {
    return language;
  }
  public void setLanguage(String language) {
    this.language = language;
  }

  public Greeting() {
    this.language = "en";
    this.firstName = "";
    this.lastName = "";
  }
  public String getSalutation() {
    if(language.equals("en")) {
      return "Hello";
    }
    else if(language.equals("fr")) {
      return "Bonjour";
    }
    else if(language.equals("es")) {
      return "Hola";
    }
    else {
      return "Hello";
    }
  }
  @Override
  public String toString() {
    String salutation = getSalutation();
    if(!lastName.isBlank() && !firstName.isBlank()) {
      return salutation + ", " + firstName + " " + lastName;
    }
    else if(!firstName.isBlank()) {
      return salutation + ", " + firstName;
    }
    else {
      return salutation;
    }
  }
}