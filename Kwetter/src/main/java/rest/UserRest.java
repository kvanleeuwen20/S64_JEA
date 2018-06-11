package rest;

import domain.User;
import domain.UserRole;
import dto.UserDTO;
import security.Secured;
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
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Produces({APPLICATION_JSON})
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

    /**
     * Route to get a specific user by id
     *
     * @param id the id to find a user with
     * @return a user in JSON format
     */
    @GET
    @Path("/findByID/{id}")
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Produces({APPLICATION_JSON})
    public UserDTO getUserByID(@PathParam("id") int id) {
        return UserDTO.fromUser(userService.getUserByID(id));
    }

    /**
     * Route to get a specific user with the username
     *
     * @param username the username to find a user with
     * @return a user in JSON format
     */
    @GET
    @Path("/findByUsername/{username}")
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Produces({APPLICATION_JSON})
    public UserDTO getUserByUsername(@PathParam("username") String username) {
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
    @POST
    @Path("/update")
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
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
    @DELETE
    @Secured(UserRole.ADMINISTRATOR)
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
    @GET
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Consumes({APPLICATION_JSON})
    @Path("/followOther/{userID}/{otherUserID}")
    public void addFollower(@PathParam("userID") int userID, @PathParam("otherUserID") int otherUserID) {
        this.userService.addFollower(userID, otherUserID);
    }

    /**
     * Route to remove an existing followOther relation in between two users
     *
     * @param username      the username of the user who is gonna un followOther someone else
     * @param otherUsername the username of the user who is going to be un followed
     */
    @GET
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Consumes({APPLICATION_JSON})
    @Path("/unfollowOther/{userID}/{otherUserID}")
    public void deleteFollower(@PathParam("userID") int userID, @PathParam("otherUserID") int otherUserID) {
        this.userService.removeFollower(userID, otherUserID);

    }
}