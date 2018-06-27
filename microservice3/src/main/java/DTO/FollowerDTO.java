package DTO;

import Domain.User;

public class FollowerDTO {
    private int id;
    private String username;
    private String profilePicturePath;

    public FollowerDTO() {

    }

    public FollowerDTO(int id, String username, String profilePicturePath) {
        this.id = id;
        this.username = username;
        this.profilePicturePath = profilePicturePath;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public static FollowerDTO fromUser(User follower) {
        return new FollowerDTO(
                follower.getID(),
                follower.getUsername(),
                follower.getProfilePicturePath()
        );
    }
}
