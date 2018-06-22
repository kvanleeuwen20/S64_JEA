package restricted;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private FilterConfig config;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/faces/restricted_login.xhtml";
        String loginServlet = request.getContextPath() + "/faces/login";



        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean loginServletRequest = request.getRequestURI().equals(loginServlet);

        if (loggedIn || loginRequest || loginServletRequest) {
            System.out.println("YOU HAVE ENTERED THIS SPOT");
            chain.doFilter(request, response);
        }
        else {
            System.out.println("YOU HAVE ENTERED THE ELSE");
            response.sendRedirect(loginURI);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
        config = null;
    }
}