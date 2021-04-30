package com.launchacademy.dogbook.services;

import com.launchacademy.dogbook.models.Breed;
import java.util.List;
import javax.persistence.EntityManager;

public class BreedService {

  private final EntityManager em;

  public BreedService(EntityManager em) {
    this.em = em;
  }

  public Breed getBreed(String breedName) {
    // find or create Breed
    Breed breed = null;
    try {
      List<Breed> breedList = em
          .createQuery("SELECT b from Breed b WHERE name = :name", Breed.class)
          .setParameter("name", breedName).setMaxResults(1).getResultList();
      if (breedList.isEmpty()) {
        // Breed doesn't exist yet, add Breed
        breed = new Breed(breedName);
        em.getTransaction().begin();
        em.persist(breed);
        em.getTransaction().commit();
      } else {
        breed = breedList.get(0);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    return breed;
  }
}
