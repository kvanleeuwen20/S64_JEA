package Service;

import DTO.MessageDTO;
import Domain.Message;
import Domain.User;
import Repository.MessageRepository;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageService() {
    }

    /**
     * Get all messages from user with given id.
     *
     * @param id the id of the poster.
     * @return a list of messages.
     */
    public List<Message> findMessagesFromUser(int id) {
        return messageRepository.findByPoster_Id(id);
    }

    /**
     * Get all messages from user with given id.
     *
     * @param id the id of the poster.
     * @return a list of messages.
     */
    public List<Message> generateTimeLine(int id) {

        return messageRepository.generateTimeLine(id);
    }

    /**
     * Get all messages where the content contains the given content.
     *
     * @param content the content to search for
     * @return a list of messages which contain the given content
     */
    public List<Message> findMessagesWhereContentLike(String content) {
        return messageRepository.findByContentLike(content);
    }

    /**
     * Posts a message.
     *
     * @param messageDTO the message to post.
     * @return the id of the new message if succesful, else -1.
     */
    public int postMessage(MessageDTO messageDTO) {
        User poster = this.userRepository.findById(messageDTO.getPoster().getId()).get();
        Message message = new Message(messageDTO.getContent(), messageDTO.getPostTime(), poster);
        return messageRepository.save(message).getId();
    }

    /**
     * Will remove an existing message.
     *
     * @param id the id of the message to remove.
     */
    public void removeMessage(int id) {
        messageRepository.deleteById(id);
    }

    /**
     * Updates a message.
     *
     * @param messageDTO the messsage to update and its updated values.
     * @return the updated message.
     */
    public Message updateMessage(MessageDTO messageDTO) {
        Message message = this.messageRepository.findById(messageDTO.getId()).get();

        message.setContent(messageDTO.getContent());

        return messageRepository.save(message);
    }
}
