package com.launchacademy.javaspringvalidations.repositories;

import com.launchacademy.javaspringvalidations.models.Idea;
import org.springframework.data.repository.CrudRepository;

public interface IdeaRepository extends CrudRepository<Idea, Integer> {
}
