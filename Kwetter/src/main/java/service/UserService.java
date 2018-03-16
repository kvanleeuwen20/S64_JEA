package service;

import dao.UserDAO;
import dao.UserDAOJPAImpl;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDao;

    public UserService() {
    }

    /**
     * Gets all users.
     *
     * @return a List of all users.
     */
    public List<User> getUsers() {
        return userDao.getAlLUsers();
    }

    /**
     * Authenticates a user by the given email and login.
     *
     * @param email the email address
     * @param password the password
     * @return the user if one is found, else null.
     */
    public User authenticate(String email, String password) {
        return userDao.findUserByLoginCredential(email, password);
    }

    /**
     * Get a user by the given username.
     *
     * @param username the username
     * @return the user if found, else null.
     */
    public User getUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    /**
     * Registers a new user.
     *
     * @param user the user to register
     * @return the id of the new user. If failed, returns -1.
     */
    public int register(User user) {
        return userDao.addUser(user);
    }

    /**
     * Removes an existing user.
     *
     * @param id the id of the user to remove.
     */
    public void remove(int id) {
        userDao.removeUser(id);
    }

    /**
     * Updates an existing user.
     *
     * @param user the user to update and the user's new data.
     * @return the new user details. Null if failed to update.
     */
    public User update(User user) {
        return userDao.updateUser(user);
    }
}
