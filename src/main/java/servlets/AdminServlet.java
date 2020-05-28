package servlets;

import models.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService.INSTANCE.deleteUser(Long.parseLong(req.getParameter("id")));
        //getServletContext().getRequestDispatcher("/mainOLD.jsp").forward(req,resp);
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserService.INSTANCE.getUserById(Long.parseLong(req.getParameter("id")));
        //UserService.INSTANCE.updateUser(req.getParameter("name"));
        resp.sendRedirect("/");
    }
}
