package com.launchacademy.donutTracker.repositories;

import com.launchacademy.donutTracker.models.Donut;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonutRepository extends PagingAndSortingRepository<Donut, Integer> {

}
