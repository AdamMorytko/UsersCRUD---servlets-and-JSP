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
import java.util.List;

@WebServlet("/list")
public class userList extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        List<User> all = userDao.findAll();
        req.setAttribute("users", all);
        log.debug("Users found in database: {}", all);
        getServletContext().getRequestDispatcher("/users/list.jsp")
                .forward(req, resp);
    }
}
