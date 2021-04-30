package com.launchacademy.greetings;

public class Greeting {

  private String firstName;
  private String lastName;
  private String language;

  public Greeting() {
    this.language = "en";
    this.firstName = "";
    this.lastName = "";
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getSalutation() {
    if (this.language.equals("en")) {
      return "Hello";
    } else if (this.language.equals("fr")) {
      return "Bonjour";
    } else if (this.language.equals("es")) {
      return "Hola";
    } else {
      return "Hello";
    }
  }

  @Override
  public String toString() {
    String salutation = getSalutation();
    if (!this.lastName.isBlank() && !this.firstName.isBlank()) {
      return salutation + ", " + this.firstName + " " + this.lastName;
    } else if (!this.firstName.isBlank()) {
      return salutation + ", " + this.firstName;
    } else {
      return salutation;
    }
  }
}