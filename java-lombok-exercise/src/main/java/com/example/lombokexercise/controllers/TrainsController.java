package com.example.lombokexercise.controllers;

import com.example.lombokexercise.models.Station;
import com.example.lombokexercise.models.Train;
import com.example.lombokexercise.models.TrainNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trains")
public class TrainsController {

  @GetMapping("/{id}")
  public String getTrain(@PathVariable Integer id, Model model) {
    Train train;
    try {
      train = getTrains().get(id - 1);
    } catch (IndexOutOfBoundsException e) {
      //throw an exception that will result in a 404
      throw new TrainNotFoundException();
    }

    //if the train is found, we can render the template
    model.addAttribute("train", train);
    return "trains/show";
  }

  @GetMapping
  public String getTrains(Model model) {
    model.addAttribute("station", getStation());
    model.addAttribute("trains", getTrains());

    return "trains/index";
  }

  private List<Train> getTrains() {
    List<Train> trains = new ArrayList<Train>();

    Train thomas = new Train();
    thomas.setName("Thomas");
    thomas.setColor("blue");
    trains.add(thomas);

    Train percy = new Train();
    percy.setColor("green");
    percy.setName("Percy");
    trains.add(percy);

    Train james = new Train();
    james.setColor("red");
    james.setName("James");
    trains.add(james);

    return trains;
  }

  private Station getStation() {
    Station knapford = new Station();
    knapford.setName("Knapford");
    knapford.setLocation("Sodor");
    System.out.println(knapford);
    return knapford;
  }
}
