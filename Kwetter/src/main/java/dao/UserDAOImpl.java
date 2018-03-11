package dao;

import domain.User;

import java.util.Set;

public class UserDAOImpl implements UserDAO {
    private Set<User> users;

    public UserDAOImpl() {

    }

    /**
     * Gets all the users of the application.
     *
     * @return a set of all the users.
     */
    @Override
    public Set<User> getAlLUsers() {
        return null;
    }

    /**
     * Add a user to the list of users.
     *
     * @param user
     * @return a boolean indicating whether the user was added.
     */
    @Override
    public boolean addUser(User user) {
        return false;
    }

    /**
     * Remove a user from the list of users.
     *
     * @param user
     */
    @Override
    public void removeUser(User user) {

    }

    /**
     * Update the information of a user.
     *
     * @param user
     * @return a boolean indicating whether the update was succesful.
     */
    @Override
    public boolean updateUser(User user) {
        return false;
    }
}
