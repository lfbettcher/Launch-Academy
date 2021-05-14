package com.launchacademy.javaspringdata.seeders;

import com.launchacademy.javaspringdata.models.AppCategory;
import com.launchacademy.javaspringdata.repositories.AppCategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppCategorySeeder implements CommandLineRunner {

  private AppCategoryRepository appCategoryRepository;

  @Autowired
  public AppCategorySeeder(AppCategoryRepository appCategoryRepository) {
    this.appCategoryRepository = appCategoryRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    // create a collection of category names
    List<String> seeder = new ArrayList<>();
    seeder.add("Social Media");
    seeder.add("Productivity");
    seeder.add("Games");
    seeder.add("Self Help");

    for (String name : seeder) {
      List appCategories = appCategoryRepository.findAllByName(name);
      if (appCategories.size() == 0) {
        AppCategory appCategory = new AppCategory();
        appCategory.setName(name);
        appCategoryRepository.save(appCategory);
      }
    }
  }
}