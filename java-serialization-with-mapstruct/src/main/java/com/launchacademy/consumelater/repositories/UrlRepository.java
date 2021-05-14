package com.launchacademy.consumelater.repositories;

import com.launchacademy.consumelater.models.Url;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends PagingAndSortingRepository<Url,Integer> {
  public List<Url> findAllByUrlStartingWith(String url);
}
