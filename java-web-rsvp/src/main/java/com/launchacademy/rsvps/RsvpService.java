package com.launchacademy.rsvps;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RsvpService {

  private final EntityManager em;

  public RsvpService(EntityManager em) {
    this.em = em;
  }

  public boolean save(Rsvp rsvp) {
    // persist record to database
    try {
      em.getTransaction().begin();
      em.persist(rsvp);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      System.out.println("Did not save to database");
      e.printStackTrace();
      return false;
    }
  }

  public List<Rsvp> showRsvps() {
    TypedQuery<Rsvp> query = em.createQuery(
        "SELECT r FROM Rsvp r ORDER BY r.lastName, r.firstName", Rsvp.class);
    return query.getResultList();
  }
}
