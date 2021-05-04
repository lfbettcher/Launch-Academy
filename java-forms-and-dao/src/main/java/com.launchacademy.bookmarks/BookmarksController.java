package com.launchacademy.bookmarks;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = {"/bookmarks/new", "/bookmarks", "/"})
public class BookmarksController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if(req.getServletPath().equals("/bookmarks/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/form.jsp");
      dispatcher.forward(req, resp);
    }
    else if(req.getServletPath().equals("/") || req.getServletPath().equals("/bookmarks")) {
      EntityManager em = getEmf().createEntityManager();
      try {
        BookmarkService bookmarkService = new BookmarkService(em);
        List<Bookmark> allBookmarks = bookmarkService.findAll();
        req.setAttribute("bookmarks", allBookmarks);
      }
      finally {
        em.close();
      }

      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/index.jsp");
      dispatcher.forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(req.getServletPath().equals("/bookmarks")) {
      // save our bookmark
      Bookmark bookmark = new Bookmark();
//      bookmark.setTitle(req.getParameterMap().get("title"));
//      System.out.println(req.getParameterMap());
//      Map<String, String> myFormInput = new HashMap<>();
//      myFormInput.put()
      try {
        BeanUtils.populate(bookmark, req.getParameterMap());
      }
      catch(IllegalAccessException ex) {
        //do some logging
      }
      catch(InvocationTargetException ex) {
        //do some logging
      }
      System.out.println(bookmark);
      System.out.println(bookmark.getTitle());
      System.out.println(bookmark.getUrl());

      EntityManagerFactory emf = getEmf();
      EntityManager em = emf.createEntityManager();

      ValidatorFactory vf = getVf();
      Validator val = vf.getValidator();

      BookmarkService service = new BookmarkService(em, val);
      if(!service.save(bookmark)) {
        System.out.println("Error: Did not save bookmark");
      } else {
        System.out.println("Successfully saved bookmark");
      }

      em.close();

      // redirect to a page
      resp.sendRedirect("/bookmarks/new");
    }
    else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  private EntityManagerFactory getEmf() {
    return (EntityManagerFactory)this.getServletContext().getAttribute("emf");
  }

  private ValidatorFactory getVf() {
    return (ValidatorFactory) this.getServletContext().getAttribute("vf");
  }
}