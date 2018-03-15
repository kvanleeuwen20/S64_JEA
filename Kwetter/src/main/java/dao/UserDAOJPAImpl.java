package dao;

import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

public class UserDAOJPAImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOJPAImpl() {

    }

    /**
     * Gets all the users of the application.
     *
     * @return a set of all the users.
     */
    @Override
    public List<User> getAlLUsers() {
        return entityManager
                .createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    /**
     * Get user by the following login credentials.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return the user if one is found. Else null.
     */
    @Override
    public User findUserByLoginCredential(String email, String password) {
        return entityManager
                .createNamedQuery("User.authenticate", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    /**
     * Find user by the given username.
     *
     * @param username the username of the user to find.
     * @return the user if found. Else null.
     */
    @Override
    public User findUserByUsername(String username) {
        return entityManager
                .createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    /**
     * Add a user to the list of users.
     *
     * @param user the user to add to the list of users.
     * @return a the users' new id.
     */
    @Override
    public int addUser(User user) {
        entityManager.persist(user);
        return user.getID();
    }

    /**
     * Remove a user from the list of users.
     *
     * @param id the id of the user to remove from the list of users.
     */
    @Override
    public void removeUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    /**
     * Update the information of a user.
     *
     * @param user the user to update and its updated details.
     * @return the updated user if successful, else null.
     */
    @Override
    public User updateUser(User user) {
        entityManager.merge(user);

        return user;
    }
}
