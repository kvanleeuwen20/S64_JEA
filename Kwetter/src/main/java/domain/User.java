package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    /**
     * Email address entered when registering.
     */
    private String email;
    /**
     * Password entered when registering.
     */
    private String password;
    /**
     * Display name towards other users. Can also be used for logging in.
     */
    private String username;
    /**
     * Real name. Can be an empty String.
     */
    private String name;
    /**
     * Website address. Can be an empty String.
     */
    private String website;
    /**
     * Location, such as place of residence or country. Can be an empty String.
     */
    private String location;
    /**
     * Short biography of the user. Can be an empty String.
     */
    private String bio;
    /**
     * Path to the profile picture of the user. Can be an empty String.
     */
    private String profilePicturePath;

    /**
     * List of users which are following this user.
     */
    private List<User> followers;
    /**
     * List of users which this user is following.
     */
    private List<User> following;

    /**
     * List of messages this user has posted.
     */
    private List<Message> messages;

    /**
     * Constructor for registering a new user.
     *
     * @param email email address used for login.
     * @param password password used for login.
     * @param username display name towards other users.
     * @param profilePicturePath path to the profile picture file. Can be null.
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
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(("Name must be a non-empty String."));
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
}
