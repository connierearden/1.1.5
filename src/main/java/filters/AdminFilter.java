package filters;

import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null &&
                (((User) session.getAttribute("user")).getRole().equalsIgnoreCase("admin"))) {

            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/");
        }
    }
}
