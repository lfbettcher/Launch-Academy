package com.launchacademy.javaspringdata.repositories;

import com.launchacademy.javaspringdata.models.AppCategory;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AppCategoryRepository extends CrudRepository<AppCategory, Integer> {

  public List<AppCategory> findAllByName(String name);
}