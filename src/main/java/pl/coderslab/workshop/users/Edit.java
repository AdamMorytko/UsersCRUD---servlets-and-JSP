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

@WebServlet("/edit")
public class Edit extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        UserDao userdao = new UserDao();
        User read = userdao.read(id);
        req.setAttribute("user", read);
        log.debug("User {} shown", read);
        getServletContext().getRequestDispatcher("/users/edit.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        UserDao userdao = new UserDao();
        User userToUpdate = userdao.read(id);
        String userName = req.getParameter("userName");
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        log.debug("User {} modified from {} to {}, from {} to {}, from {} to {}",
                userToUpdate, userToUpdate.getUserName(), userName,
                userToUpdate.getEmail(), email,
                userToUpdate.getPassword(), password);
        if (userName.equalsIgnoreCase("")) {
            userName = userToUpdate.getUserName();
        }
        if (email.equalsIgnoreCase("")) {
            email = userToUpdate.getEmail();
        }
        if (password.equalsIgnoreCase("")) {
            password = userToUpdate.getPassword();
        }
        userToUpdate.setUserName(userName);
        userToUpdate.setEmail(email);
        userToUpdate.setPassword(password);
        userdao.update(userToUpdate);
        resp.sendRedirect("/user/list");
    }
}
