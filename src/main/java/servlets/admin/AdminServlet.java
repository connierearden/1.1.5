package servlets.admin;

import models.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.INSTANCE.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/adminHome.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                Long.parseLong(req.getParameter("id")),
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("age")),
                req.getParameter("pass"),
                req.getParameter("role"));
        UserService.INSTANCE.addUser(user);
        /*doGet(req,resp);*/
        resp.sendRedirect("/admin/");
    }

}
