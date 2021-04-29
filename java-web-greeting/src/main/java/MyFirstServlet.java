import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet(urlPatterns = "/hello")
public class MyFirstServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String languageGreeting = "Hello";
    String lang = req.getParameter("lang");
    if ("fr".equals(lang)) {
      languageGreeting = "Bonjour";
    } else if ("es".equals(lang)) {
      languageGreeting = "Hola";
    }
    String greeting = languageGreeting + " from a servlet backed JSP";
    String firstName = req.getParameter("first_name");
    String lastName = req.getParameter("last_name");
    if (firstName != null && !firstName.isBlank()) {
      greeting += ", " + firstName;
      if (lastName != null && !lastName.isBlank()) {
        greeting += " " + lastName;
      }
    }

    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/greetings/show.jsp");
    req.setAttribute("greeting", greeting);
    req.setAttribute("lang", lang);
    dispatcher.forward(req, resp);
  }
}