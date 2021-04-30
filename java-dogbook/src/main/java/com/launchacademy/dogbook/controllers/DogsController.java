package com.launchacademy.dogbook.controllers;

import com.launchacademy.dogbook.models.Dog;
import com.launchacademy.dogbook.services.BreedService;
import com.launchacademy.dogbook.services.DogService;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = {"/dogs", "/dogs/new"})
public class DogsController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getServletPath().equals("/dogs")) {
      // get list of all dogs
      EntityManagerFactory emf = getEmf();
      EntityManager em = emf.createEntityManager();
      DogService dogService = new DogService(em);

      List<Dog> dogs = dogService.findAll();
      req.setAttribute("dogs", dogs);

      // custom headline
      req.setAttribute("sessionDog", req.getSession().getAttribute("newDog"));
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/dogs/index.jsp");
      dispatcher.forward(req, resp);
      em.close();
    } else if (req.getServletPath().equals("/dogs/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/dogs/new.jsp");
      dispatcher.forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    if (req.getServletPath().equals("/dogs")) {
      Dog newDog = new Dog();
      // map user input JSON data to dog object
      try {
        BeanUtils.populate(newDog, req.getParameterMap());
      } catch (InvocationTargetException | IllegalAccessException e) {
        e.printStackTrace();
      }

      // create connections to database
      EntityManagerFactory emf = getEmf();
      EntityManager em = emf.createEntityManager();

      // get and set dog's breed id
      String breedName = req.getParameter("breedName");
      BreedService breedService = new BreedService(em);
      newDog.setBreed(breedService.getBreed(breedName));

      // persist dog
      DogService dogService = new DogService(em);
      // save session and redirect if saved
      if (dogService.save(newDog)) {
        HttpSession session = req.getSession();
        session.setAttribute("newDog", newDog);
        resp.sendRedirect("/dogs");
      } else {
        System.out.println("Could not save dog");
      }
      em.close();
    }
  }

  private EntityManagerFactory getEmf() {
    return (EntityManagerFactory) this.getServletContext().getAttribute("emf");
  }
}
