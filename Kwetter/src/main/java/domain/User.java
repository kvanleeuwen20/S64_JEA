package domain;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A user of the application who has created an account.
 */
public class User {

    /**
     * Email address entered when registering.
     */
    protected String email;
    /**
     * Password entered when registering.
     */
    protected String password;
    /**
     * Display name towards other users. Can also be used for logging in.
     */
    protected String username;
    /**
     * Real name. Can be an empty String.
     */
    protected String name;
    /**
     * Website address. Can be an empty String.
     */
    protected String website;
    /**
     * Location, such as place of residence or country. Can be an empty String.
     */
    protected String location;
    /**
     * Short biography of the user. Can be an empty String.
     */
    protected String bio;
    /**
     * Path to the profile picture of the user. Can be an empty String.
     */
    protected String profilePicturePath;

    /**
     * List of users which are following this user.
     */
    protected List<User> followers;
    /**
     * List of users which this user is following.
     */
    protected List<User> following;

    /**
     * List of messages this user has posted.
     */
    protected List<Message> messages;

    /**
     * Constructor for registering a new user.
     *
     * @param email email address used for login.
     * @param password password used for login.
     * @param username display name towards other users.
     * @param name the real name of the user. Can be an empty String.
     * @param profilePicturePath path to the profile picture file. Can be an empty String.
     */
    public User(String email, String password, String username, String name, String profilePicturePath) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(("Email must be a non-empty String."));
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException(("Password must be a non-empty String."));
        }
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException(("Username must be a non-empty String."));
        }
        if (name == null) {
            throw new IllegalArgumentException(("Name cannot be null."));
        }
        if (profilePicturePath == null) {
            throw new IllegalArgumentException(("ProfilePicturePath cannot be null."));
        }

        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.profilePicturePath = profilePicturePath;

        this.website = "";
        this.location = "";
        this.bio = "";

        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();

        this.messages = new ArrayList<>();
    }

    /**
     * Create a copy of the user. Used for upgrading a user to a moderator.
     *
     * @param user the user to upgrade
     */
    protected User(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.name = user.getName();
        this.profilePicturePath = user.getProfilePicturePath();

        this.website = user.getWebsite();
        this.location = user.getLocation();
        this.bio = user.getBio();

        this.followers = user.getFollowers();
        this.following = user.getFollowing();

        this.messages = user.getMessages();
    }

    /**
     * Get email of user.
     * @return email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email address. Cannot be an empty String.
     * @param email new email address
     */
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(("Email must be a non-empty String."));
        }

        this.email = email;
    }

    /**
     * Get password of user.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password of user. Cannot be an empty String.
     * @param password new password
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException(("Password must be a non-empty String."));
        }

        this.password = password;
    }

    /**
     * Get website of user.
     * @return website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set website of user.
     * @param website the new website. Can be an empty String.
     */
    public void setWebsite(String website) {
        if (website == null) {
            throw new IllegalArgumentException(("Website cannot be null."));
        }

        this.website = website;
    }

    /**
     * Get username of user.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username of user. Cannot be an empty String.
     * @param username new username.
     */
    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException(("Username must be a non-empty String."));
        }

        this.username = username;
    }

    /**
     * Get name of user.
     * @return real name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of user.
     * @param name new name. Can be an empty String.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(("Name must be a non-empty String."));
        }

        this.name = name;
    }

    /**
     * Get location of user.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set location of user.
     * @param location new location. Can be an empty String.
     */
    public void setLocation(String location) {
        if (location == null) {
            throw new IllegalArgumentException(("Location cannot be null."));
        }

        this.location = location;
    }

    /**
     * Get biography of the user.
     * @return bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * Set biography of user.
     * @param bio new bio. Can be an empty String.
     */
    public void setBio(String bio) {
        if (bio == null) {
            throw new IllegalArgumentException(("Bio cannot be null."));
        }

        this.bio = bio;
    }

    /**
     * Get path to the profile picture.
     * @return profile picture path
     */
    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    /**
     * Set path to the profile picture.
     * @param profilePicturePath new profile picture path. Can be an empty String.
     */
    public void setProfilePicturePath(String profilePicturePath) {
        if (profilePicturePath == null) {
            throw new IllegalArgumentException(("ProfilePicturePath cannot be null."));
        }

        this.profilePicturePath = profilePicturePath;
    }

    /**
     * Return a copy of the list of followers.
     *
     * @return followers
     */
    public List<User> getFollowers() {
        List<User> returnList = new ArrayList<User>();
        Collections.copy(returnList, this.followers);
        return returnList;
    }

    /**
     * Return a copy of the list of following.
     *
     * @return following
     */
    public List<User> getFollowing() {
        List<User> returnList = new ArrayList<User>();
        Collections.copy(returnList, this.following);
        return returnList;
    }

    /**
     * Return a copy of the list of messages posted by this user.
     *
     * @return messages
     */
    public List<Message> getMessages() {
        List<Message> returnList = new ArrayList<Message>();
        Collections.copy(returnList, this.messages);
        return returnList;
    }

    /**
     * Check if this user is a moderator.
     *
     * @return a boolean. When true the usr is a moderator.
     */
    public boolean isModerator() {
        return this instanceof Moderator;
    }

    /**
     * Start following this user.
     *
     * @param toFollow the user to follow. When this user is already following the toFollow, nothing happens.
     *                 If the user is trying to follow himself, an exception is thrown.
     */
    public void follow(User toFollow) {
        throw new UnsupportedOperationException();
    }

    /**
     * Stop following this user.
     *
     * @param toUnfollow the user to unfollow. If this user is not following the toUnfollow, nothing happens.
     */
    public void unfollow(User toUnfollow) {
        throw new UnsupportedOperationException();
    }
}
