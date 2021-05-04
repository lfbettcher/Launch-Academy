package com.example.challenge.javaspringdiceroller.controllers;

import com.example.challenge.javaspringdiceroller.models.DiceRoll;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollsController {

  @GetMapping("/rolls/new")
  public String getNew(@RequestParam(required = false) Integer guess,
      @RequestParam(required = false) Integer diceCount, Model model) {
    int numDice = diceCount == null ? 1 : diceCount;
    // roll many
    List<Integer> rolls = DiceRoll.getRollsList(numDice);
    // get sum and remove from list
    int sum = rolls.get(rolls.size() - 1);
    rolls.remove(rolls.size() - 1);
    model.addAttribute("rolls", rolls);
    model.addAttribute("sum", sum);

    model.addAttribute("guess", guess);

    if (guess == null) {
      return "show";
    }

    if (guess == sum) {
      model.addAttribute("message", "YOU GUESSED THE NUMBER");
      model.addAttribute("correct", true);
    } else if (guess < sum) {
      model.addAttribute("message", "Nope. Sorry. Try Again");
    } else {
      model.addAttribute("message",
          "Close. If we were playing price is right rules, you would have won.");
    }
    //render src/main/webapp/rolls/show.jsp
    return "show";
  }
}
