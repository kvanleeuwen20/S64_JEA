package dao;

import domain.Message;

import javax.ejb.Stateless;
import java.util.List;

/**
 * DAO interface which manages all the messages.
 */
@Stateless
public interface MessageDAO {
    /**
     * Gets all the messages of the application.
     *
     * @return a set of all the messages.
     */
    List<Message> getAllMessages();

    /**
     * Find all messages from a given user.
     *
     * @param id the id of the message.
     * @return all found messages. If none are found, returns an empty List.
     */
    List<Message> findMessagesFromUserId(int id);

    /**
     * Finds all messages which match the given content.
     *
     * @param content the content of the message.
     * @return all found messages. If none are found, returns an empty List.
     */
    List<Message> findMessagesWhereContentLike(String content);

    /**
     * Add a user to the list of messages.
     *
     * @param message the message to add.
     * @return a boolean indicating whether the message was added.
     */
    boolean addMessage(Message message);

    /**
     * Remove a message from the list of messages.
     *
     * @param message the message to remove.
     */
    void removeMessage(Message message);

    /**
     * Update the information of a message.
     *
     * @param message the message to update with ita updated details.
     * @return a boolean indicating whether the update was succesful.
     */
    boolean updateMessage(Message message);
}
