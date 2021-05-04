package com.launchacademy.bookmarks;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@WebListener
public class EMFListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.javaDatabaseApplications");
    event.getServletContext().setAttribute("emf", emf);

    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    event.getServletContext().setAttribute("vf", vf);
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    EntityManagerFactory emf = (EntityManagerFactory)event.getServletContext().getAttribute("emf");
    emf.close();

    ValidatorFactory vf = (ValidatorFactory) event.getServletContext().getAttribute("vf");
    vf.close();
  }
}