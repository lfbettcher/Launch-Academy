package com.launchacademy.javaspringdata.repositories;

import com.launchacademy.javaspringdata.models.Idea;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IdeaRepository extends CrudRepository<Idea, Integer> {

  public Idea findByName(String name);

  public List<Idea> findAllByName(String name);

  @Query("SELECT i from Idea i where i.name like :namePrefix%")
  public Idea whereNameStartsWith(@Param("namePrefix") String namePrefix);

  public List<Idea> findByNameStartingWith(String namePrefix);

  public List<Idea> findByNameContaining(String nameSegment);
}