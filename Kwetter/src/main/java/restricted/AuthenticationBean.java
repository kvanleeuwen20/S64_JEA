package restricted;

import dao.UserDAO;
import domain.UserRole;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean(name = "authenticationBean", eager = true)
public class AuthenticationBean {

    @Inject
    private UserDAO dao;

    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    private String password;
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isLoggedIn(String pass) {
        if (getPassword() != null) {
            return FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().get(pass) == password;
        }
        else {
            return false;
        }
    }

    public String login() {
        domain.User user = dao.findUserByUsername(name);
        if (user.getPassword() == password && user.getRole() == UserRole.ADMINISTRATOR) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    password, name);
            return "success";
        }
        else {
            return "failure";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .remove(password);
        return null;
    }
}
