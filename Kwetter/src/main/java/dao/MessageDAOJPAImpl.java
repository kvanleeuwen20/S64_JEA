package dao;

import domain.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

public class MessageDAOJPAImpl implements MessageDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public MessageDAOJPAImpl(){
        
    }

    /**
     * Gets all the messages of the application.
     *
     * @return a set of all the messages.
     */
    @Override
    public Set<Message> getAllMessages() {
        return null;
    }

    /**
     * Add a user to the list of messages.
     *
     * @param message the message to add.
     * @return a boolean indicating whether the message was added.
     */
    @Override
    public boolean addMessage(Message message) {
        return false;
    }

    /**
     * Remove a message from the list of messages.
     *
     * @param message the message to remove.
     */
    @Override
    public void removeMessage(Message message) {

    }

    /**
     * Update the information of a message.
     *
     * @param message the message to update with ita updated details.
     * @return a boolean indicating whether the update was succesful.
     */
    @Override
    public boolean updateMessage(Message message) {
        return false;
    }
}
