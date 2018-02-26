package domain;

/**
 * Moderator class. A moderator is a user with extra rights. No extra information, but extra actions.
 */
public class Moderator extends User {
    /**
     * Constructor for registering a new moderator.
     *
     * @param email              email address used for login.
     * @param password           password used for login.
     * @param username           display name towards other users.
     * @param name
     * @param profilePicturePath path to the profile picture file. Can be null.
     */
    public Moderator(String email, String password, String username, String name, String profilePicturePath) {
        super(email, password, username, name, profilePicturePath);
    }
}
