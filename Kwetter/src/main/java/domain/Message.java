package domain;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * A message which is posted by a user on the application.
 */
@Entity
@Table(name = "Message")
@NamedQueries({
        @NamedQuery(
                name = "Message.findAllFromUser",
                query = "SELECT m FROM Message m WHERE m.poster.id = :userId ORDER BY m.postTime DESC"
        ),
        @NamedQuery(
                name = "Message.findAllMessagesFromFollowing",
                query = "SELECT m FROM Message m WHERE m.poster.id IN (SELECT f.id FROM App_User u LEFT JOIN u.following f WHERE u.id = :userId) ORDER BY m.postTime DESC"
        ),
        @NamedQuery(
                name = "Message.findAllWhereContentLike",
                query = "SELECT m FROM Message m WHERE LOWER(m.content) LIKE LOWER(:content) "
        )
})
public class Message {

    /**
     * Unique identifier for a message.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The limit on the amount of characters which can go into a single message.
     */
    public static final int CHARACTERLIMIT = 140;

    /**
     * The actual message text. Can not be an empty String and can have a maximum of 140 characters.
     */
    private String content;

    /**
     * Set of HashTags which were placed in the message. One hashtag can only be added once.
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<HashTag> hashTags;

    /**
     * Set of liked which were handed out by Users. One User can only like a message once.
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<User> likes;

    /**
     * Set of Users who are mentioned in the message. One user can only be mentioned once.
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<User> mentions;

    /**
     * The user who posted the message
     */
    @ManyToOne
    private User poster;

    /**
     * The time the message was posted.
     */
    private GregorianCalendar postTime;

    public Message() {

    }

    /**
     * Create a new message.
     *
     * @param content  the text of the message. Must be 1-140 characters.
     * @param postTime the time the message was posted. Cannot be in the future.
     * @param poster   the user who posted the message.
     */
    public Message(String content, GregorianCalendar postTime, User poster) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content must be a non-empty String.");
        }
        if (content.length() > CHARACTERLIMIT) {
            throw new IllegalArgumentException("Content must be smaller than 140 characters.");
        }
        if (postTime == null) {
            throw new IllegalArgumentException("PostTime cannot be null.");
        }
        if (postTime.compareTo(new GregorianCalendar()) > 0) {
            throw new IllegalArgumentException("PostTime cannot be in the future.");
        }
        if (poster == null) {
            throw new IllegalArgumentException("Poster cannot be null.");
        }

        this.content = content;
        this.postTime = postTime;
        this.poster = poster;

        //TODO - Determine hashTags and mentions based on the content.

        this.hashTags = new HashSet<>();
        this.mentions = new HashSet<>();
        this.likes = new HashSet<>();
    }

    /**
     * Get the content of the message.
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the hashTags in this message.
     *
     * @return a copy of the list of hashTags
     */
    public Set<HashTag> getHashTags() {
        return new HashSet<>(this.hashTags);
    }

    /**
     * Get the id of the message.
     *
     * @return the ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the likes in this message.
     *
     * @return a copy of the list of users who liked the message.
     */
    public Set<User> getLikes() {
        return new HashSet<>(this.likes);
    }

    /**
     * Get the mentions in this message.
     *
     * @return a copy of the list of mentions
     */
    public Set<User> getMentions() {
        return new HashSet<>(this.mentions);
    }

    /**
     * Get the poster of the message.
     *
     * @return the user who posted this message
     */
    public User getPoster() {
        return poster;
    }

    /**
     * Get the date and time of the message.
     *
     * @return post time
     */
    public GregorianCalendar getPostTime() {
        return postTime;
    }

    /**
     * Set the content of the message.
     *
     * @param content the new content. Cannot be an empty String and can have a maximum of 140 chars.
     */
    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content must be a non-empty String and smaller than 140 characters");
        }
        if (content.length() > CHARACTERLIMIT) {
            throw new IllegalArgumentException("Content must be smaller than 140 characters.");
        }

        this.content = content;
    }

    /**
     * Like a post.
     *
     * @param like the user who likes the post.
     */
    public boolean like(User like) {
        if (!likes.contains(like)) {
            return this.likes.add(like);
        }

        return false;
    }

    /**
     * Unlike a post.
     *
     * @param unlike the user who likes the post.
     */
    public void unlike(User unlike) {
        this.likes.remove(unlike);
    }

    /**
     * Add a hashtag.
     *
     * @param hashTag the hashtag to add. Cannot be an empty String.
     */
    private void addHashtag(HashTag hashTag) {
        this.hashTags.add(hashTag);
    }

    /**
     * Add a mention.
     *
     * @param mention the user to mention.
     */
    private boolean addMention(User mention) {
        if (!this.mentions.contains(mention)) {
            return this.mentions.add(mention);
        }

        return false;
    }
}
