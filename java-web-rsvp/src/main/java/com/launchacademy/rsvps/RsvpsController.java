package com.launchacademy.rsvps;

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

@WebServlet(urlPatterns = {"/rsvps", "/rsvps/new"})
public class RsvpsController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getServletPath().equals("/rsvps/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/rsvps/new.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/rsvps")) {
      // show list of all rsvps
      EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
      EntityManager em = emf.createEntityManager();
      RsvpService rsvpService = new RsvpService(em);

      List<Rsvp> rsvps = rsvpService.showRsvps();
      req.setAttribute("rsvps", rsvps);

      RequestDispatcher dispatcher =
          req.getRequestDispatcher("/views/rsvps/listOfRsvps.jsp");
      dispatcher.forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    // create connections to database
    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
    EntityManager em = emf.createEntityManager();

    RsvpService rsvpService = new RsvpService(em);
    // instantiate new rsvp object to be persisted
    Rsvp newRsvp = new Rsvp();

    // map JSON data coming in to Rsvp object
    try {
      BeanUtils.populate(newRsvp, req.getParameterMap());
    } catch (InvocationTargetException | IllegalAccessException e) {
      e.printStackTrace();
    }
    rsvpService.save(newRsvp);

    em.close();
    resp.sendRedirect("/rsvps");
  }
}
