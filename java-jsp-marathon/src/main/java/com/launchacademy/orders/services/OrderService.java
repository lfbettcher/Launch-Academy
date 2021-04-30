package com.launchacademy.orders.services;

import com.launchacademy.orders.models.Order;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class OrderService {

  private final EntityManager em;

  public OrderService(EntityManager em) {
    this.em = em;
  }

  public boolean save(Order order) {
    // persist record to database
    try {
      em.getTransaction().begin();
      em.persist(order);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      System.out.println("Did not save to database");
      e.printStackTrace();
      return false;
    }
  }

  public List<Order> showOrders() {
    TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
    return query.getResultList();
  }

  public Order findOrder(Long id) {
    TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.id = :id", Order.class);
    query.setParameter("id", id);
    return query.getSingleResult();
  }
}
