package rest;

import domain.User;
import dto.UserDTO;
import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
public class UserRest {

    @Inject
    private UserService userService;

    public UserRest() {

    }

    /**
     * Route to get all users
     *
     * @return the list of all users in JSON format
     */
    @GET
    @Path("/")
    @Produces({APPLICATION_JSON})
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

    /**
     * Route to get a specific user with the username
     *
     * @param username the username to find a user with
     * @return a user in JSON format
     */
    @GET
    @Path("/{username}")
    @Produces({APPLICATION_JSON})
    public UserDTO getUserByUsername(@PathParam("username") String username) {
        return UserDTO.fromUser(userService.getUserByUsername(username));
    }

    /**
     * Route to sign up a new user
     * <p>
     * Required request body JSON format:
     * {
     * "username": "username",
     * "password": "password"
     * }
     * <p>
     * All above fields are required!
     *
     * @param user user instance serialized from request body
     * @return user instance in JSON format when successfully saved in the db
     */
    @POST
    @Path("/signUp/{user}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public int signUp(User user) {

        User user2 = new User("m.weesenaar@student.fontys.nl", "Narcos<3", "Weesje123", "Maikel", "/img/3_0001");
        return userService.register(user2);
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
    @PUT
    @Path("/update/{user}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public UserDTO update(User user) {
        return UserDTO.fromUser(userService.update(user));
    }

    /**
     * Route to authenticated user with login credentials.
     * <p>
     * Required request body JSON format:
     * {
     * "username": "username",
     * "password": "password"
     * }
     * <p>
     * All above fields are required!
     *
     * @param email email
     * @param password password
     * @return user instance in JSON format when found in the db
     */
    @POST
    @Path("/authenticate/{email}/{password}")
    @Produces({APPLICATION_JSON})
    public UserDTO authenticate(@PathParam("email") String email, @PathParam("password") String password) {
        return UserDTO.fromUser(userService.authenticate(email, password));
    }

    /**
     * Route to delete existing user
     * <p>
     * Will return successful response without content (204) on success
     *
     * @param id the id of the user who needs to be deleted from the db
     */
    @DELETE
    @Consumes({APPLICATION_JSON})
    @Path("/remove/{id}")
    public void delete(@PathParam("id") int id) {
        userService.remove(id);
    }

    /**
     * Route to add a new followOther relation in between two users
     *
     * @param username      the username of the user who is gonna followOther someone else
     * @param otherUsername the username of the user who is going to be followed
     */
    @POST
    @Consumes({APPLICATION_JSON})
    @Path("/followOther/{username}/{otherUsername}")
    public void addFollower(@PathParam("username") String username, @PathParam("otherUsername") String otherUsername) {
        User user1 = userService.getUserByUsername(username);
        User user2 = userService.getUserByUsername(otherUsername);

        System.out.println(user1.getUsername());
        System.out.println(user2.getUsername());

        if (user1 == null || user2 == null) {
            return;
        }

        user1.followOther(user2);
        userService.update(user1);
        System.out.println(user1);
    }

    /**
     * Route to remove an existing followOther relation in between two users
     *
     * @param username      the username of the user who is gonna un followOther someone else
     * @param otherUsername the username of the user who is going to be un followed
     */
    @POST
    @Consumes({APPLICATION_JSON})
    @Path("/unfollowOther/{username}/{otherUsername}")
    public void deleteFollower(@PathParam("username") String username, @PathParam("otherUsername") String otherUsername) {
        User user1 = userService.getUserByUsername(username);
        User user2 = userService.getUserByUsername(otherUsername);

        if (user1 == null || user2 == null) {
            return;
        }

        user1.unfollowOther(user2);
        userService.update(user1);
    }
}