import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/example")
public class ExampleController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    boolean isSignedIn = true;
    String userName = "jsnow";
    int nightsWatchSupporters = 1;
    String[] favoriteThings = {
        "wolves",
        "ice",
        "swords"
    };

    req.setAttribute("isSignedIn", isSignedIn);
    req.setAttribute("userName", userName);
    req.setAttribute("nightsWatchSupporters", nightsWatchSupporters);
    req.setAttribute("favoriteThings", favoriteThings);

    if(req.getParameter("useJSTL") != null) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/example/show.jsp");
      dispatcher.forward(req, resp);
    }
    else {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/example/showJava.jsp");
      dispatcher.forward(req,resp);
    }


  }
}
