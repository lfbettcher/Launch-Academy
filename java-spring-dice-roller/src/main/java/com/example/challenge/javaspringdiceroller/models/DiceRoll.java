package com.example.challenge.javaspringdiceroller.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class DiceRoll {

  public static int roll(int min, int max) {
    Random rand = new Random();
    return rand.nextInt(max - min) + min;
  }

  public static List<Integer> getRollsList(int diceCount) {
    List<Integer> rolls = new ArrayList<>();
    int sum = 0;
    for (int i = 0; i < diceCount; i++) {
      int num = roll(1, 6);
      rolls.add(num);
      sum += num;
    }
    // last item in list is sum. extract and remove it
    rolls.add(sum);
    return rolls;
  }
}
