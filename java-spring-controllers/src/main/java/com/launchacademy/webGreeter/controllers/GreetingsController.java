package com.launchacademy.webGreeter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/greetings")
public class GreetingsController {

  @GetMapping("/default")
  public String getDefaultGreeting(Model model) {
    model.addAttribute("greeting", "Hello from Spring");
    return "greetings/show";
  }

  /* not preferred way
  @GetMapping("/default")
  public ModelAndView getDefaultGreeting(Model model) {
    ModelAndView modelAndView = new ModelAndView("greetings/show");
    modelAndView.addObject("greeting", "Hello from Spring");
    return modelAndView;
  }*/

  @GetMapping("/by-query-string")
  public String getQueryStringGreeting(@RequestParam String name, Model model) {
    model.addAttribute("greeting", "Hello from Spring, " + name);
    return "greetings/show";
  }

  @GetMapping("/by-name/{name}")
  public String getCustomizedGreeting(@PathVariable String name, Model model) {
    model.addAttribute("greeting", "Hello from Spring, " + name);
    return "greetings/show";
  }
}
