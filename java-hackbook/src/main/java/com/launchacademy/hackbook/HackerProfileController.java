package com.launchacademy.hackbook;

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
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = {"/hacker-profiles", "/hacker-profiles/new"})
public class HackerProfileController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getServletPath().equals("/hacker-profiles/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/hacker-profiles/new.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/hacker-profiles")) {
      // show list of all hackers
      EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
      EntityManager em = emf.createEntityManager();
      HackerProfileService profileService = new HackerProfileService(em);

      List<HackerProfile> profiles = profileService.showHackers();
      req.setAttribute("profiles", profiles);

      RequestDispatcher dispatcher =
          req.getRequestDispatcher("/views/hacker-profiles/listOfHackers.jsp");
      dispatcher.forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    // create connections to database
    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
    EntityManager em = emf.createEntityManager();

    HackerProfileService hackerProfileService = new HackerProfileService(em);
    // instantiate new hacker object to be persisted
    HackerProfile newHacker = new HackerProfile();

    // map JSON data coming in to hacker object
    try {
      BeanUtils.populate(newHacker, req.getParameterMap());
    } catch (InvocationTargetException | IllegalAccessException e) {
      e.printStackTrace();
    }
    hackerProfileService.save(newHacker);

    em.close();
    resp.sendRedirect("/hacker-profiles");
  }
}
