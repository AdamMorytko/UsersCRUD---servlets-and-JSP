package pl.coderslab.workshop.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.coderslab.workshop.entity.User;
import pl.coderslab.workshop.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/show")
public class Show extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        UserDao userdao = new UserDao();
        User read = userdao.read(id);
        req.setAttribute("user", read);
        log.debug("User {} shown", read);
        getServletContext().getRequestDispatcher("/users/show.jsp")
                .forward(req, resp);
    }
}
