package Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A user of the application who has created an account.
 */
@Entity(name = "App_User")
@Table(name = "App_User")
@NamedQueries({
        @NamedQuery(name = "App_User.findAll", query = "SELECT u FROM App_User u"),
        @NamedQuery(name = "App_User.findByUsername", query = "SELECT u FROM App_User u WHERE u.username = :username"),
        @NamedQuery(name = "App_User.findByEmail", query = "SELECT u FROM App_User u WHERE u.email = :email"),
        @NamedQuery(name = "App_User.findByID", query = "SELECT u FROM App_User u WHERE u.id = :id"),
        @NamedQuery(
                name = "App_User.authenticate",
                query = "SELECT u FROM App_User u WHERE u.email = :email AND u.password = :password"
        )
})
public class User implements Serializable{

    /**
     * Unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    /**
     * Email address entered when registering.
     */
    @Column(unique = true, nullable = false)
    protected String email;
    /**
     * Password entered when registering.
     */
    @Column(nullable = false)
    protected String password;
    /**
     * Display name towards other users. Can also be used for logging in.
     */
    @Column(unique = true, nullable = false)
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
    @Lob
    protected String profilePicturePath;
    /**
     * Role of the user.
     */
    @Enumerated(EnumType.STRING)
    protected UserRole role;
    /**
     * List of users which are following this user.
     */
    @ManyToMany(mappedBy = "following", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    protected List<User> followers;
    /**
     * List of users which this user is following.
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    protected List<User> following;

    /**
     * Empty constructor for JPA
     */
    public User(){

    }

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

        this.role = UserRole.USER;

        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    /**
     * Get biography of the user.
     * @return bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * Get email of user.
     * @return email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Return a copy of the list of followers.
     *
     * @return followers
     */
    public List<User> getFollowers() {
        return new ArrayList<>(this.followers);
    }

    /**
     * Return a copy of the list of following.
     *
     * @return following
     */
    public List<User> getFollowing() {
        return new ArrayList<>(this.following);
    }

    /**
     * Get id of user.
     * @return id.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Get location of user.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Get name of user.
     * @return real name
     */
    public String getName() {
        return name;
    }

    /**
     * Get password of user.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get path to the profile picture.
     * @return profile picture path
     */
    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    /**
     * Get role of the user.
     * @return role of the user as an enum value.
     */
    public UserRole getRole() {
        return this.role;
    }

    /**
     * Get username of user.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get website of user.
     * @return website
     */
    public String getWebsite() {
        return website;
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
     * Set email address. Cannot be an empty String.
     * @param email new email address
     */
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must be a non-empty String.");
        }

        this.email = email;
    }

    /**
     * Add the userID of a follower to the list of followers
     * @param follower The user account of the person following you.
     */
    public void setFollower(User follower) {
        this.followers.add(follower);
    }

    /**
     * Add an User to list that is being followed.
     * @param following The user added to the list.
     */
    private void setFollowing(User following){
        if (following == null) {
            throw new IllegalArgumentException("following cannot be null");
        }

        this.following.add(following);
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
     * Set role of the user.
     * @param role the new role as an enum value.
     */
    public void setRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }
        this.role = role;
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
     * Check if this user is a moderator.
     *
     * @return a boolean. When true the usr is a moderator.
     */
    public boolean isModerator() {
        return this.getRole() == UserRole.ADMINISTRATOR;
    }

    /**
     * Start following this user.
     *
     * @param toFollow the user to followOther. When this user is already following the toFollow, nothing happens.
     *                 If the user is trying to follow himself, an exception is thrown.
     */
    public void followOther(User toFollow) {
        if (toFollow == null) {
            throw new IllegalArgumentException("toFollow cannot be null.");
        }
        if (toFollow.id == this.id) {
            throw new IllegalArgumentException("User cannot follow himself.");
        }

        if (!this.following.contains(toFollow)) {
            this.following.add(toFollow);
            toFollow.getFollowed(this);
        }
    }

    /**
     * Stop following this user.
     *
     * @param toUnfollow the user to unfollowOther. If this user is not following the toUnfollow, nothing happens.
     */
    public void unfollowOther(User toUnfollow) {
        if (toUnfollow == null) {
            throw new IllegalArgumentException("toUnfollow cannot be null.");
        }

        this.following.remove(toUnfollow);
        toUnfollow.getUnfollowed(this);
    }

    private void getFollowed(User follower) {
        if (follower == null) {
            throw new IllegalArgumentException("follower cannot be null.");
        }

        if (follower.id == this.id) {
            throw new IllegalArgumentException("User cannot follow himself.");
        }

        if (!this.followers.contains(follower)) {
            this.followers.add(follower);
        }

    }

    private void getUnfollowed(User unfollower) {
        if (unfollower == null) {
            throw new IllegalArgumentException("unfollower cannot be null.");
        }

        this.followers.remove(unfollower);
    }
}
