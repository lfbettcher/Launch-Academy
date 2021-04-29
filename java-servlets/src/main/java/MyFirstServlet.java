import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class MyFirstServlet extends HttpServlet {

  // uses RequestDispatcher instead of PrintWriter
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String greeting = "Hello from a servlet backed JSP";
    String firstName = req.getParameter("first_name");
    if (firstName != null && firstName != "") {
      greeting += ", " + firstName;
    }
    // continually set up variable `greeting` rather than conditionally write to PrintWriter
    // this view will not automatically have access to `greeting` variable
    // need to send that data to jsp file using setAttribute to hand variable down to view
    // keeps business logic in controller (in this case servlet) and send data to view (jsp)
    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
    req.setAttribute("greeting", greeting);
    dispatcher.forward(req, resp);
  }

  /*
  // verbose, hard to read and maintain, mixes responsibilities of business logic and presentation
  // servlet should not be responsible for HTML generation
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();
    String firstName = req.getParameter("first_name");

    resp.setContentType("text/html");
    writer.println("<!DOCTYPE HTML>");
    writer.println("<html>");
    writer.println("  <head>");
    writer.println("    <title>First Servlet</title>");
    writer.println("  </head>");
    writer.println("  <body>");

    if (firstName != null && firstName != "") {
      writer.println("<h1>Hello from a servlet, " + firstName);
    } else {
      writer.println("<h1>Hello from a servlet</h1>");
    }

    writer.println("  </body>");
    writer.println("</html>");
  }*/

  /* // contrived and invalid HTML markup
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // Query String: http://localhost:8080/hello?first_name=Elizabeth
    PrintWriter writer = resp.getWriter();
    String firstName = req.getParameter("first_name");
    if (firstName != null && firstName != "") {
      writer.println("<h1>Hello from a servlet, " + firstName + "</h1>");
    } else {
      writer.println("<h1>Hello from a servlet</h1>");
    }
  }*/
}