package com.launchacademy.greetings;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/greetings/new", "/greetings", "/", "/greetings/forget"})
public class GreetingsController extends HttpServlet {

  // uses sessions
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    if (req.getServletPath().equals("/greetings/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/new.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/")) {
      req.setAttribute("cookieGreeting", req.getSession().getAttribute("greeting"));
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/index.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/greetings/forget")) {
      HttpSession session = req.getSession();
      session.setAttribute("greeting", null);
      resp.sendRedirect("/");
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getServletPath().equals("/greetings")) {
      Greeting greeting = new Greeting();
      greeting.setFirstName(req.getParameter("firstName"));
      greeting.setLastName(req.getParameter("lastName"));
      greeting.setLanguage(req.getParameter("language"));
      req.setAttribute("greeting", greeting);

      //new code
      HttpSession session = req.getSession();
      session.setAttribute("greeting", greeting.toString());

      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
      dispatcher.forward(req, resp);
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  /* uses cookies
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    if (req.getServletPath().equals("/greetings/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/new.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/")) {
      String cookieGreeting = null;
      for (Cookie cookie : req.getCookies()) {
        if (cookie.getName().equals("greeting")) {
          cookieGreeting = cookie.getValue();
        }
      }
      req.setAttribute("cookieGreeting", cookieGreeting);
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/index.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/greetings/forget")) {
      Cookie foundCookie = null;
      for (Cookie cookie : req.getCookies()) {
        if (cookie.getName().equals("greeting")) {
          foundCookie = cookie;
          break;
        }
      }
      if (foundCookie != null) {
        foundCookie.setMaxAge(0);
        foundCookie.setPath("/");
        resp.addCookie(foundCookie);
      }
      resp.sendRedirect("/");
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getServletPath().equals("/greetings")) {
      Greeting greeting = new Greeting();
      greeting.setFirstName(req.getParameter("firstName"));
      greeting.setLastName(req.getParameter("lastName"));
      greeting.setLanguage(req.getParameter("language"));
      req.setAttribute("greeting", greeting);

      //new code
      Cookie greetingCookie = new Cookie("greeting", greeting.toString());
      greetingCookie.setMaxAge(60 * 60 * 24);
      resp.addCookie(greetingCookie);

      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
      dispatcher.forward(req, resp);
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }*/
}