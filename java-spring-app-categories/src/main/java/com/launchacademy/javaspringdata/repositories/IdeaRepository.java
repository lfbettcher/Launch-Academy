package com.launchacademy.javaspringdata.repositories;

import com.launchacademy.javaspringdata.models.Idea;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IdeaRepository extends CrudRepository<Idea, Integer> {
  List<Idea> findAllByName(String name);
  List<Idea> findAll();
  Idea findByName(String name);
  List<Idea> findByDescriptionNotContaining(String string);
  List<Idea> findByNameStartingWithIgnoreCase(String string);
  List<Idea> findByNameEndingWith(String string);
}