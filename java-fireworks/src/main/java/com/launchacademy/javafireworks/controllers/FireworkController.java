package com.launchacademy.javafireworks.controllers;

import com.launchacademy.javafireworks.services.FireworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fireworks")
public class FireworkController {

  private FireworkService fireworkService;

  @Autowired
  public FireworkController(FireworkService fireworkService) {
    this.fireworkService = fireworkService;
  }

  @GetMapping
  public String getFireworks(Model model) {
    model.addAttribute("fireworks", fireworkService.findAll());
    return "fireworks/index";
  }

  @GetMapping("/{id}")
  public String showFirework(@PathVariable Integer id, Model model) {
    model.addAttribute("firework", fireworkService.get(id));
    return "fireworks/show";
  }
}
