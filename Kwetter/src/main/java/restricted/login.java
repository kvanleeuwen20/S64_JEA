package restricted;

import domain.User;
import service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@ManagedBean(name = "loginBean", eager = true)
@WebServlet("/login")
class LoginServlet extends HttpServlet {

//    private String name;
//    private String password;
//
//    private boolean LoggedIn;

    @EJB
    private UserService userService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LOGINGET");
        request.getRequestDispatcher("/protected/home.xhtml").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("USERNAME"+username);
        String password = request.getParameter("password");
        System.out.println("PASSWORD"+password);
        Map<String, String> messages = new HashMap<String, String>();

        if (username == null || username.isEmpty()) {
            messages.put("username", "Please enter username");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }

        if (messages.isEmpty()) {
            User user = userService.authenticate(username, password);

            if (user != null) {
                System.out.println("LOGGED IN CORRECTLY");
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("/protected/home.xhtml").forward(request, response);
//                response.sendRedirect(request.getContextPath() + "/home");
                return;
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}