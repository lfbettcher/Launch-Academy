package com.launchacademy.bookmarks;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = {"/bookmarks/new", "/bookmarks", "/"})
public class BookmarksController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (req.getServletPath().equals("/bookmarks/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/new.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/")) {
      EntityManager em = getEmf().createEntityManager();
      try {
        BookmarkService bookmarkService = new BookmarkService(em);
        req.setAttribute("bookmarks", bookmarkService.findAll());
      } finally {
        em.close();
      }
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/index.jsp");
      dispatcher.forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (req.getServletPath().equals("/bookmarks")) {
      Bookmark bookmark = new Bookmark();
      try {
        BeanUtils.populate(bookmark, req.getParameterMap());
      } catch (IllegalAccessException ex) {
        //do some logging
      } catch (InvocationTargetException ex) {
        //do some logging
      }

      EntityManagerFactory emf = getEmf();
      EntityManager em = emf.createEntityManager();

      BookmarkService service = new BookmarkService(em);
      if (!service.save(bookmark)) {
        //do some error logging, re-render the form, etc
      }
      resp.sendRedirect("/bookmarks/new");
      em.close();
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  private EntityManagerFactory getEmf() {
    return (EntityManagerFactory) this.getServletContext().getAttribute("emf");
  }
}