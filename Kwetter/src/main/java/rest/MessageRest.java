package rest;

import domain.Message;
import domain.UserRole;
import dto.MessageDTO;
import security.Secured;
import service.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/messages")
public class MessageRest {

    @Context
    private SecurityContext securityContext;

    @Inject
    private MessageService messageService;

    public MessageRest() {

    }

    /**
     * Get all messages from user with given id.
     *
     * @param id the id of the poster.
     * @return a list of messages.
     */
    @GET
    //@Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Path("/{id}")
    @Produces({APPLICATION_JSON})
    public List<MessageDTO> findMessagesFromUser(@PathParam("id") int id) {
        return messageService.findMessagesFromUser(id).stream().map(MessageDTO::fromMessage).collect(Collectors.toList());
    }

    /**
     * Get all messages from user with given id.
     *
     * @param id the id of the poster.
     * @return a list of messages.
     */
    @GET
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Path("/timeline/{id}")
    @Produces({APPLICATION_JSON})
    public List<MessageDTO> getTimeLine(@PathParam("id") int id) {
        return messageService.generateTimeLine(id).stream().map(MessageDTO::fromMessage).collect(Collectors.toList());
    }

    /**
     * Get all messages where the content contains the given content.
     *
     * @param content the content to search for
     * @return a list of messages which contain the given content
     */
    @GET
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Path("/findMessagesLike/{content}")
    @Produces({APPLICATION_JSON})
    public List<MessageDTO> findMessagesWhereContentLike(String content) {
        return messageService.findMessagesWhereContentLike(content).stream().map(MessageDTO::fromMessage).collect(Collectors.toList());
    }

    /**
     * Posts a message.
     *
     * @param message the message to post.
     * @return the id of the new message if succesful, else -1.
     */
    @POST
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Path("/post")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public int postMessage(MessageDTO message) {
        return messageService.postMessage(message);
    }

    /**
     * Will remove an existing message.
     *
     * @param id the id of the message to remove.
     */
    @DELETE
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Path("/remove/{id}")
    public void removeMessage(int id) {
        messageService.removeMessage(id);
    }

    /**
     * Updates a message.
     *
     * @param message the messsage to update and its updated values.
     * @return the updated message.
     */
    @POST
    @Secured({UserRole.USER, UserRole.ADMINISTRATOR})
    @Path("/update/{message}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public MessageDTO updateMessage(Message message) {
        return MessageDTO.fromMessage(messageService.updateMessage(message));
    }
}
