package dao;

import domain.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public List<Message> getAllMessages() {
        return entityManager
                .createNamedQuery("Message.findAll", Message.class)
                .getResultList();
    }

    /**
     * Find all messages from a given user.
     *
     * @param id the id of the message.
     * @return all found messages. If none are found, returns an empty List.
     */
    @Override
    public List<Message> findMessagesFromUserId(int id) {
        return entityManager
                .createNamedQuery("Message.findAllFromUser", Message.class)
                .setParameter("userId", id)
                .getResultList();
    }

    /**
     * Finds all messages which match the given content.
     *
     * @param content the content of the message.
     * @return all found messages. If none are found, returns an empty List.
     */
    @Override
    public List<Message> findMessagesWhereContentLike(String content) {
        return entityManager
                .createNamedQuery("Message.findAllWhereContentLike", Message.class)
                .setParameter("content", content)
                .getResultList();
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
