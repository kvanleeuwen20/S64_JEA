package dao;

import domain.Message;

import java.util.Set;

/**
 * DAO interface which manages all the messages.
 */
public interface MessageDAO {
    /**
     * Gets all the messages of the application.
     *
     * @return a set of all the messages.
     */
    Set<Message> getAllMessages();

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
