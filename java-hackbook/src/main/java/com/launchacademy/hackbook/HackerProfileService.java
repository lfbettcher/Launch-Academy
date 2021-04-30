package com.launchacademy.hackbook;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class HackerProfileService {

  private final EntityManager em;

  public HackerProfileService(EntityManager em) {
    this.em = em;
  }

  public boolean save(HackerProfile profile) {
    // persist record to database
    try {
      em.getTransaction().begin();
      em.persist(profile);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      System.out.println("Did not save to database");
      e.printStackTrace();
      return false;
    }
  }

  public List<HackerProfile> showHackers() {
    TypedQuery<HackerProfile> query = em.createQuery(
        "SELECT p FROM HackerProfile p ORDER BY p.lastName, p.firstName", HackerProfile.class);
    return query.getResultList();
  }
}
