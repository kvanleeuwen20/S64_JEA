package dto;

import domain.User;

public class FollowerDTO {
    private int userID;
    private String username;
    private String profilePicturePath;

    public FollowerDTO(int userID, String username, String profilePicturePath) {
        this.userID = userID;
        this.username = username;
        this.profilePicturePath = profilePicturePath;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
