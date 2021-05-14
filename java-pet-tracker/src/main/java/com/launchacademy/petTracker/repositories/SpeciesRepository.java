package com.launchacademy.petTracker.repositories;

import com.launchacademy.petTracker.models.Species;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends PagingAndSortingRepository<Species, Integer> {

  Optional<Species> findByName(String name);
}
