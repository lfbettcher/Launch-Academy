package com.launchacademy.javaspringandreact.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping(value = {"/client", "/additional-path"})
  public String forward() {
    return "forward:/";
  }
}
