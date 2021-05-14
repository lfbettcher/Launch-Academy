package com.launchacademy.donutTracker.controllers;

import com.launchacademy.donutTracker.models.Donut;
import com.launchacademy.donutTracker.repositories.DonutRepository;
import com.launchacademy.donutTracker.services.DonutService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donuts")
public class DonutsController {

  private final DonutRepository donutRepository;
  private final DonutService donutService;

  @Autowired
  public DonutsController(DonutRepository donutRepository, DonutService donutService) {
    this.donutRepository = donutRepository;
    this.donutService = donutService;
  }

  @GetMapping
  public String getDonuts(Pageable pageable, Model model) {
    model.addAttribute("donuts", donutRepository.findAll(pageable));
    return "donuts/index";
  }

  @GetMapping("/new")
  public String newDonut(@ModelAttribute Donut donut) {
    return "donuts/new";
  }

  @PostMapping
  public String addDonut(@ModelAttribute @Valid Donut donut, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "donuts/new";
    } else {
      donutRepository.save(donut);
      return "redirect:/donuts";
    }
  }
}
