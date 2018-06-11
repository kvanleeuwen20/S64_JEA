package service;

import dao.MessageDAO;
import dao.UserDAO;
import domain.Message;
import domain.User;
import dto.MessageDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class MessageService {

    @Inject
    private MessageDAO messageDAO;

    @Inject
    private UserDAO userDAO;

    public MessageService() {

    }

    /**
     * Get all messages from user with given id.
     *
     * @param id the id of the poster.
     * @return a list of messages.
     */
    public List<Message> findMessagesFromUser(int id) {
        return messageDAO.findMessagesFromUserId(id);
    }

    /**
     * Get all messages from user with given id.
     *
     * @param id the id of the poster.
     * @return a list of messages.
     */
    public List<Message> generateTimeLine(int id) {

        return messageDAO.generateTimeLine(id);
    }

    /**
     * Get all messages where the content contains the given content.
     *
     * @param content the content to search for
     * @return a list of messages which contain the given content
     */
    public List<Message> findMessagesWhereContentLike(String content) {
        return messageDAO.findMessagesWhereContentLike(content);
    }

    /**
     * Posts a message.
     *
     * @param messageDTO the message to post.
     * @return the id of the new message if succesful, else -1.
     */
    public int postMessage(MessageDTO messageDTO) {
        User poster = this.userDAO.findUserByID(messageDTO.getPoster().getId());
        Message message = new Message(messageDTO.getContent(), messageDTO.getPostTime(), poster);
        return messageDAO.addMessage(message);
    }

    /**
     * Will remove an existing message.
     *
     * @param id the id of the message to remove.
     */
    public void removeMessage(int id) {
        messageDAO.removeMessage(id);
    }

    /**
     * Updates a message.
     *
     * @param message the messsage to update and its updated values.
     * @return the updated message.
     */
    public Message updateMessage(Message message) {
        return messageDAO.updateMessage(message);
    }
}
