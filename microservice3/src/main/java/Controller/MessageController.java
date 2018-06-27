package Controller;

import DTO.MessageDTO;
import Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * Get all messages from user with given id.
     *
     * @param id the id of the poster.
     * @return a list of messages.
     */
    @RequestMapping(method= RequestMethod.GET, value="/messages/{id}")
    public List<MessageDTO> findMessagesFromUser(@PathVariable("id") int id) {
        return messageService.findMessagesFromUser(id).stream().map(MessageDTO::fromMessage).collect(Collectors.toList());
    }

    /**
     * Get all messages from user with given id.
     *
     * @param id the id of the poster.
     * @return a list of messages.
     */
    @RequestMapping(method= RequestMethod.GET, value="/messages/timeline/{id}")
    public List<MessageDTO> getTimeLine(@PathVariable("id") int id) {
        return messageService.generateTimeLine(id).stream().map(MessageDTO::fromMessage).collect(Collectors.toList());
    }

    /**
     * Get all messages where the content contains the given content.
     *
     * @param content the content to search for
     * @return a list of messages which contain the given content
     */
    @RequestMapping(method= RequestMethod.GET, value="/findMessagesLike/{content}")
    public List<MessageDTO> findMessagesWhereContentLike(@PathVariable("content") String content) {
        return messageService.findMessagesWhereContentLike(content).stream().map(MessageDTO::fromMessage).collect(Collectors.toList());
    }

    /**
     * Posts a message.
     *
     * @param message the message to post.
     * @return the id of the new message if succesful, else -1.
     */
    @RequestMapping(method= RequestMethod.POST, value="/post")
    public int postMessage(MessageDTO message) {
        return messageService.postMessage(message);
    }

    /**
     * Will remove an existing message.
     *
     * @param id the id of the message to remove.
     */
    @RequestMapping(method= RequestMethod.DELETE, value="/remove")
    public void removeMessage(int id) {
        messageService.removeMessage(id);
    }

    /**
     * Updates a message.
     *
     * @param message the messsage to update and its updated values.
     * @return the updated message.
     */
    @RequestMapping(method= RequestMethod.POST, value="/update")
    public MessageDTO updateMessage(MessageDTO message) {
        return MessageDTO.fromMessage(messageService.updateMessage(message));
    }
}
