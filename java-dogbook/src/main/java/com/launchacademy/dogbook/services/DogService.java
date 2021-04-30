package com.launchacademy.dogbook.services;

import com.launchacademy.dogbook.models.Dog;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DogService {

  private final EntityManager em;

  public DogService(EntityManager em) {
    this.em = em;
  }

  public boolean save(Dog dog) {
    // persist record to database
    try {
      em.getTransaction().begin();
      em.persist(dog);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      System.out.println("Did not save to database");
      e.printStackTrace();
      return false;
    }
  }

  public List<Dog> findAll() {
    TypedQuery<Dog> query = em.createQuery(
        "SELECT d FROM Dog d ORDER BY d.lastName, d.firstName", Dog.class);
    return query.getResultList();
  }
}
