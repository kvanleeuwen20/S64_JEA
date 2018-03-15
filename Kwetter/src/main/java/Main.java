import dao.UserDAO;
import dao.UserDAOJPAImpl;

public class Main {
    public static void main(String[] args) {
        UserDAO dao = new UserDAOJPAImpl();
        System.out.println(dao.getAlLUsers().size());
    }
}
