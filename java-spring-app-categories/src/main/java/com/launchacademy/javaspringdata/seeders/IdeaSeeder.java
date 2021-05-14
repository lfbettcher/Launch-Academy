package com.launchacademy.javaspringdata.seeders;

import com.launchacademy.javaspringdata.models.Idea;
import com.launchacademy.javaspringdata.repositories.IdeaRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IdeaSeeder implements CommandLineRunner {

  private IdeaRepository ideaRepository;

  @Autowired
  public IdeaSeeder(IdeaRepository ideaRepository) {
    this.ideaRepository = ideaRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    //create a collection of creative names and descriptions
    Map<String, String> seedMap = new HashMap<String, String>();
    seedMap.put("Dogbook", "Facebook, but for dogs");
    seedMap.put("Dogstagram", "Instagram, but for dogs");
    seedMap.put("Cat Twitter", "Twitter, but for Dogs to make fun of cats");

    //loop through the collection, creating app ideas if they're not found
    for (String name : seedMap.keySet()) {
      List ideas = ideaRepository.findAllByName(name);
      if (ideas.size() == 0) {
        Idea idea = new Idea();
        idea.setName(name);
        idea.setDescription(seedMap.get(name));
        ideaRepository.save(idea);
      }
    }

    System.out.println("All Ideas");
    System.out.println(ideaRepository.findAll());

    System.out.println("Specific Idea by id 1");
    System.out.println(ideaRepository.findById(1));

    System.out.println("Specific Idea by name 'Cat Twitter'");
    System.out.println(ideaRepository.findByName("Cat Twitter"));

    System.out.println("An Idea which doesn't contain the word 'Facebook'");
    System.out.println(ideaRepository.findByDescriptionNotContaining("Facebook").get(0));

    System.out.println("All Ideas that start with 'Cat' case insensitive");
    System.out.println(ideaRepository.findByNameStartingWithIgnoreCase("Cat"));

    System.out.println("All Ideas that end with 'book'");
    System.out.println(ideaRepository.findByNameEndingWith("book"));
  }
}