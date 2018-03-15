package dao;

import domain.User;

import javax.ejb.Stateless;
import java.util.List;

/**
 * DAO interface which manages all the users.
 */
@Stateless
public interface UserDAO {

    /**
     * Gets all the users of the application.
     *
     * @return a list of all the users.
     */
    List<User> getAlLUsers();

    /**
     * Get user by the following login credentials.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return the user if one is found. Else null.
     */
    User findUserByLoginCredential(String email, String password);

    /**
     * Find user by the given username.
     *
     * @param username the username of the user to find.
     * @return the user if found. Else null.
     */
    User findUserByUsername(String username);

    /**
     * Add a user to the list of users.
     *
     * @param user the user to add to the list of users.
     * @return a the users' new id.
     */
    int addUser(User user);

    /**
     * Remove a user from the list of users.
     *
     * @param id the id of the user to remove from the list of users.
     */
    void removeUser(int id);

    /**
     * Update the information of a user.
     *
     * @param user the user to update and its updated details.
     * @return the updated user if successful, else null.
     */
    User updateUser(User user);
}
