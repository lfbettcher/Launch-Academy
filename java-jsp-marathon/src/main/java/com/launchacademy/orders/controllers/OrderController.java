package com.launchacademy.orders.controllers;

import com.launchacademy.orders.models.Order;
import com.launchacademy.orders.services.OrderService;
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

@WebServlet(urlPatterns = {"/", "/orders/new"})
public class OrderController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getServletPath().equals("/orders/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/orders/new.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/orders")) {
      EntityManager em = getEmf().createEntityManager();
      OrderService orderService = new OrderService(em);

      List<Order> orders = orderService.showOrders();
      req.setAttribute("orders", orders);

      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/orders/index.jsp");
      dispatcher.forward(req, resp);
      em.close();
    } else if (req.getParameter("orderId") != null && !req.getParameter("orderId").isBlank()) {
      try {
        Long orderIdNum = Long.parseLong(req.getParameter("orderId"));
        EntityManager em = getEmf().createEntityManager();
        OrderService orderService = new OrderService(em);
        Order foundOrder = orderService.findOrder(orderIdNum);
        req.setAttribute("order", foundOrder);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/orders/show.jsp");
        dispatcher.forward(req, resp);
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }

    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    // create connections to database
    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
    EntityManager em = emf.createEntityManager();

    OrderService hackerProfileService = new OrderService(em);
    // instantiate new hacker object to be persisted
    Order newOrder = new Order();

    // map JSON data coming in to hacker object
    try {
      BeanUtils.populate(newOrder, req.getParameterMap());
    } catch (InvocationTargetException | IllegalAccessException e) {
      e.printStackTrace();
    }
    hackerProfileService.save(newOrder);

    em.close();
    resp.sendRedirect("/orders");
  }

  private EntityManagerFactory getEmf() {
    return (EntityManagerFactory) this.getServletContext().getAttribute("emf");
  }
}