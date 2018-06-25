package Service;

import DTO.UserDTO;
import Domain.User;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    /**
     * Gets all users.
     *
     * @return a List of all users.
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Get a user by the given username.
     *
     * @param username the username
     * @return the user if found, else null.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    /**
     * Get a user by the given id.
     *
     * @param id the id
     * @return the user if found, else null.
     */
    public User getUserByID (int id) {
        return userRepository.findById(id).get();
    }

    /**
     * Removes an existing user.
     *
     * @param id the id of the user to remove.
     */
    public void remove(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Updates the general info (username, name, bio, website) of an existing user.
     *
     * @param userDTO the user to update and the user's new data.
     * @return the new user details. Null if failed to update.
     */
    public User update(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).get();

        user.setBio(userDTO.getBio());
        user.setWebsite(userDTO.getWebsite());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());

        return userRepository.save(user);
    }

    public void addFollower(int userID, int otherUserID) {
        User user = this.userRepository.findById(userID).get();
        User otherUser = this.userRepository.findById(otherUserID).get();

        user.followOther(otherUser);

        this.userRepository.save(user);
        this.userRepository.save(otherUser);
    }

    public void removeFollower(int userID, int otherUserID) {
        User user = this.userRepository.findById(userID).get();
        User otherUser = this.userRepository.findById(otherUserID).get();

        user.unfollowOther(otherUser);

        this.userRepository.save(user);
        this.userRepository.save(otherUser);
    }
}
