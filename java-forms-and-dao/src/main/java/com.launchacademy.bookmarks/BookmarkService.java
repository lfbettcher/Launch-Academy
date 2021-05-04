package com.launchacademy.bookmarks;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

public class BookmarkService {
  private EntityManager em;
  private Validator val;

  public BookmarkService(EntityManager em) {
    this.em = em;
  }
  public BookmarkService(EntityManager em, Validator val) {
    this.em = em;
    this.val = val;
  }

  public boolean save(Bookmark bookmark) {
    boolean valid = true;

    Set<ConstraintViolation<Bookmark>> violations = val.validate(bookmark);
    if(violations.size() > 0) {
      valid = false;
      System.out.println("Invalid input: please address the following issues");
      for (ConstraintViolation<Bookmark> violation : violations) {
        System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
      }
    }

    if(valid) {
      try {
        em.getTransaction().begin();
        em.persist(bookmark);
        em.getTransaction().commit();
        return true;
      } catch (Exception exc) {
        em.getTransaction().rollback();
        return false;
      }
    }

    return false;
  }

  public List<Bookmark> findAll() {
    TypedQuery<Bookmark> query = em.createQuery("SELECT b FROM Bookmark b", Bookmark.class);
    return query.getResultList();
  }


//  public List<String> formattedBookmarkDescriptions() {
//    List<Bookmark> allBookmarks = this.findAll();
//
//    return allBookmarks;
//  }

//  public Bookmark find(int id) {
//
//  }
//
//  public Bookmark findByTitle(String title) {
//
//  }
}