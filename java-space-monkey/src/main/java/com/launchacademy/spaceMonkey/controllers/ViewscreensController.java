package com.launchacademy.spaceMonkey.controllers;

import com.launchacademy.spaceMonkey.models.Viewscreen;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/viewscreens")
public class ViewscreensController {

  @GetMapping
  public String listScreens(Model model) {
    List viewscreens = getList();
    model.addAttribute("viewscreens", viewscreens);
    return "viewscreens/index";
  }

  @GetMapping("/{id}")
  public String showScreens(@PathVariable int id, Model model) {
    List viewscreens = getList();
    Object current;
    current = viewscreens.get(id);
    model.addAttribute("current", current);
    return "viewscreens/show";
  }

  private List getList() {
    Viewscreen main = new Viewscreen();
    main.setId(0);
    main.setName("Main Viewer");
    main.setRole("The looky see thing");
    main.setLocation("Bridge");
    main.setBananas(5000);

    Viewscreen engine = new Viewscreen();
    engine.setId(1);
    engine.setName("Engine Terminal");
    engine.setRole("Engine make go");
    engine.setLocation("Engineering");
    engine.setBananas(50);

    List viewscreens = new ArrayList<Viewscreen>();
    viewscreens.add(main);
    viewscreens.add(engine);
    return viewscreens;
  }
}


