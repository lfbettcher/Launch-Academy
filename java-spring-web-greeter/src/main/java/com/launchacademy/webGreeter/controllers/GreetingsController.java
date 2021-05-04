package com.launchacademy.webGreeter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/greetings")
public class GreetingsController {

  @GetMapping("/default")
  public String getDefaultGreeting(Model model) {
    model.addAttribute("greeting", "Hello from Spring");
    return "greetings/show";
  }

  @GetMapping("/by-query-string")
  public String getQueryStringGreeting(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName, Model model) {
    String greeting = "Hello from Spring";
    if (firstName != null) {
      greeting += ", " + firstName;
      if (lastName != null) {
        greeting += " " + lastName;
      }
    }
    model.addAttribute("greeting", greeting);
    return "greetings/show";
  }

  @GetMapping("/by-language/{lang}")
  public String getGreetingByLanguage(@PathVariable String lang, Model model) {
    String greet = "";
    switch (lang) {
      case "fr":
        greet = "Bonjour";
        break;
      case "es":
        greet = "Hola";
        break;
      case "en":
        greet = "Hello";
        break;
      default:
        greet = "Invalid language";
        break;
    }
    model.addAttribute("greeting", greet + " from Spring");
    return "greetings/show";
  }

  @GetMapping("/by-name/{name}")
  public String getCustomizedGreeting(@PathVariable String name, Model model) {
    model.addAttribute("greeting", "Hello from Spring, " + name);
    return "greetings/show";
  }
}
