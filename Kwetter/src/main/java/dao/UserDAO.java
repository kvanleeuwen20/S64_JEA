package dao;

import domain.User;

import java.util.Set;

/**
 * DAO interface which manages all the users.
 */
public interface UserDAO {

    /**
     * Gets all the users of the application.
     *
     * @return a set of all the users.
     */
    Set<User> getAlLUsers();

    /**
     * Add a user to the list of users.
     *
     * @param user the user to add to the list of users.
     * @return a boolean indicating whether the user was added.
     */
    boolean addUser(User user);

    /**
     * Remove a user from the list of users.
     *
     * @param user the user to remove from the list of users.
     */
    void removeUser(User user);

    /**
     * Update the information of a user.
     *
     * @param user the user to update and its updated details.
     * @return a boolean indicating whether the update was succesful.
     */
    boolean updateUser(User user);
}
