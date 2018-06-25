package Controller;

import DTO.UserDTO;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Route to get all users
     *
     * @return the list of all users in JSON format
     */
    @RequestMapping(method= RequestMethod.GET, value="/users/")
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

    /**
     * Route to get a specific user by id
     *
     * @param id the id to find a user with
     * @return a user in JSON format
     */
    @RequestMapping(method= RequestMethod.GET, value="/users/findByID/{id}")
    public UserDTO getUserByID(@PathVariable("id") int id) {
        return UserDTO.fromUser(userService.getUserByID(id));
    }

    /**
     * Route to get a specific user with the username
     *
     * @param username the username to find a user with
     * @return a user in JSON format
     */
    @RequestMapping(method= RequestMethod.GET, value="/users/findByUsername/{username}")
    public UserDTO getUserByUsername(@PathVariable("username") String username) {
        return UserDTO.fromUser(userService.getUserByUsername(username));
    }

    /**
     * Route update an existing user's data
     * <p>
     * Required request body JSON format:
     * {
     * "id": 1,
     * "password": "updated",
     * "username": "updated",
     * "bio": "updated",
     * "web": "updated",
     * "imageUrls": "updated"
     * }
     * <p>
     * Only the id fields are required, all other field will get updated
     * <p>
     * Note: field with the value of null get updated as well so make sure to include values who don't need to get updated
     *
     * @param user user instance serialized from request body
     * @return user instance including in JSON format when successfully saved in the db
     */
    @RequestMapping(method= RequestMethod.POST, value="/users/update")
    public UserDTO update(UserDTO user) {
        return UserDTO.fromUser(userService.update(user));
    }

    /**
     * Route to delete existing user
     * <p>
     * Will return successful response without content (204) on success
     *
     * @param id the id of the user who needs to be deleted from the db
     */
    @RequestMapping(method= RequestMethod.DELETE, value="/delete")
    public void delete(@PathVariable("id") int id) {
        userService.remove(id);
    }

    /**
     * Route to add a new followOther relation in between two users
     *
     * @param userID      the id of the user who is gonna followOther someone else
     * @param otherUserID the id of the user who is going to be followed
     */
    @RequestMapping(method= RequestMethod.GET, value="/users/followOther/{userID}/{otherUserID}")
    public void addFollower(@PathVariable("userID") int userID, @PathVariable("otherUserID") int otherUserID) {
        this.userService.addFollower(userID, otherUserID);
    }

    /**
     * Route to remove an existing followOther relation in between two users
     *
     * @param userID      the id of the user who is gonna un followOther someone else
     * @param otherUserID the id of the user who is going to be un followed
     */
    @RequestMapping(method= RequestMethod.GET, value="/users/unfollowOther/{userID}/{otherUserID}")
    public void deleteFollower(@PathVariable("userID") int userID, @PathVariable("otherUserID") int otherUserID) {
        this.userService.removeFollower(userID, otherUserID);

    }
}
