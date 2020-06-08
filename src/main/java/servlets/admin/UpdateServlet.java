package servlets.admin;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/update/*")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] path = req.getPathInfo().split("/");
        long id = Long.parseLong(path[path.length - 1]);

        req.setAttribute("user", UserService.INSTANCE.getUserById(id));

        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO десь нужно просто принимать обновленые данные пользователя!

        UserService.INSTANCE.updateUser(
                Long.parseLong(req.getParameter("id")),
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("age")),
                req.getParameter("pass"),
                req.getParameter("role")
        );

        resp.sendRedirect("/admin/");




        /* User user = UserService.INSTANCE.getUserById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        resp.sendRedirect("/admin/update/");
        UserService.INSTANCE.updateUser(
                Long.parseLong(req.getParameter("id")),
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("age")),
                req.getParameter("pass"),
                req.getParameter("role"));
        resp.sendRedirect("/admin/");
        getServletContext().getRequestDispatcher("/WEB-INF/adminUpdateUser.jsp").forward(req, resp);*/
    }
}
