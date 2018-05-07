package rest;

import domain.Message;
import dto.MessageDTO;
import service.MessageService;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/messages")
@ManagedBean(name = "MessageRest", eager = true)
public class MessageRest {

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
    @Path("/{id}")
    @Produces({APPLICATION_JSON})
    public List<MessageDTO> findMessagesFromUser(@PathParam("id") int id) {
        return messageService.findMessagesFromUser(id).stream().map(MessageDTO::fromMessage).collect(Collectors.toList());
    }

    /**
     * Get all messages where the content contains the given content.
     *
     * @param content the content to search for
     * @return a list of messages which contain the given content
     */
    @GET
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
    @Path("/post/{message}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public int postMessage(Message message) {
        return messageService.postMessage(message);
    }

    /**
     * Will remove an existing message.
     *
     * @param id the id of the message to remove.
     */
    @DELETE
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
    @Path("/update/{message}")
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public MessageDTO updateMessage(Message message) {
        return MessageDTO.fromMessage(messageService.updateMessage(message));
    }
}
