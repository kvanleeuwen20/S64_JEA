package dto;

import domain.User;
import domain.UserRole;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private int id;
    private String email;
    private String username;
    private String website;
    private String bio;
    private String profilePicturePath;
    private UserRole role;
    private List<FollowerDTO> following;
    private List<FollowerDTO> followers;

    public UserDTO(int id, String email, String username, String website, String bio, String profilePicturePath, UserRole role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.website = website;
        this.bio = bio;
        this.profilePicturePath = profilePicturePath;
        this.role = role;
    }

    public UserDTO(int id, String email, String username, String web, String bio, String imageUrl, UserRole role, List<FollowerDTO> following, List<FollowerDTO> followers) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.website = website;
        this.bio = bio;
        this.profilePicturePath = profilePicturePath;
        this.role = role;
        this.following = following;
        this.followers = followers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicturePath() {
        return this.profilePicturePath;
    }

    public void setProfilePicPath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public UserRole getRole() {
        return this.role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // No need for copy, since that is already handled in the domain.
    public List<FollowerDTO> getFollowing() {
        return this.following;
    }

    public void setFollowing(List<FollowerDTO> following) {
        this.following = following;
    }

    // No need for copy, since that is already handled in the domain.
    public List<FollowerDTO> getFollowers() {
        return this.followers;
    }

    public void setFollowedBy(List<FollowerDTO> followers) {
        this.followers = followers;
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(
                user.getID(),
                user.getEmail(),
                user.getUsername(),
                user.getWebsite(),
                user.getBio(),
                user.getProfilePicturePath(),
                user.getRole(),
                user.getFollowing().stream().map(FollowerDTO::fromUser).collect(Collectors.toList()),
                user.getFollowers().stream().map(FollowerDTO::fromUser).collect(Collectors.toList())
        );
    }

    // This one will return a UserDto without followers and following
    public static UserDTO fromUserWithoutFollowers(User user) {
        return new UserDTO(
                user.getID(),
                user.getEmail(),
                user.getUsername(),
                user.getWebsite(),
                user.getBio(),
                user.getProfilePicturePath(),
                user.getRole()
        );
    }
}
