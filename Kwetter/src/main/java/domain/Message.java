package domain;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * A message which is posted by a user on the application.
 */
public class Message {
    /**
     * The limit on the amount of characters which go into a single message.
     */
    public static final int CHARACTERLIMIT = 140;

    /**
     * The actual message text. Can not be an empty String and can have a maximum of 140 characters.
     */
    private String content;
    /**
     * The time the message was posted.
     */
    private GregorianCalendar postTime;
    /**
     * The user who posted the message
     */
    private User poster;
    /**
     * Set of HashTags which were placed in the message. One hashtag can only be added once.
     */
    private Set<String> hashtags;
    /**
     * Set of Users who were mentioned in the message. One user can only be mentioned once.
     */
    private Set<User> mentions;
    /**
     * Set of liked which were handed out by Users. One User can only like a message once.
     */
    private Set<User> likes;

    /**
     * Create a new message.
     *
     * @param content the text of the message. Must be 1-140 characters.
     * @param postTime the time the message was posted. Cannot be in the future.
     * @param poster the user who posted the message.
     */
    public Message(String content, GregorianCalendar postTime, User poster) {
        if (content == null || content.isEmpty() || content.length() >= CHARACTERLIMIT) {
            throw new IllegalArgumentException("Content must be a non-empty String and smaller than 140 characters");
        }
        if (postTime == null) {
            throw new IllegalArgumentException("PostTime cannot be null.");
        }
        if (postTime.compareTo(new GregorianCalendar()) > 0) {
            throw new IllegalArgumentException("PostTime cannot be in the future");
        }
        if (poster == null) {
            throw new IllegalArgumentException("Poster cannot be null.");
        }

        this.content = content;
        this.postTime = postTime;
        this.poster = poster;

        //TODO - Determine hashtags and mentions based on the content.

        this.hashtags = new HashSet<>();
        this.mentions = new HashSet<>();
        this.likes = new HashSet<>();
    }

    /**
     * Get the content of the message.
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content of the message.
     * @param content the new content. Cannot be an empty String and can have a maximum of 140 chars.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the date and time of the message.
     * @return post time
     */
    public GregorianCalendar getPostTime() {
        return postTime;
    }

    /**
     * Get the poster of the message.
     * @return the user who posted this message
     */
    public User getPoster() {
        return poster;
    }

    /**
     * Get the hashtags in this message.
     * @return a copy of the list of hashtags
     */
    public Set<String> getHashtags() {
        return new HashSet<>(this.hashtags);
    }

    /**
     * Get the mentions in this message.
     * @return a copy of the list of mentions
     */
    public Set<User> getMentions() {
        return new HashSet<>(this.mentions);
    }

    /**
     * Get the likes in this message.
     * @return a copy of the list of users who liked the message.
     */
    public Set<User> getLikes() {
        return new HashSet<>(this.likes);
    }

    /**
     * Like a post.
     *
     * @param like the user who likes the post.
     */
    public void like(User like) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unlike a post.
     *
     * @param unlike the user who likes the post.
     */
    public void unlike(User like) {
        throw new UnsupportedOperationException();
    }

    /**
     * Add a hashtag.
     *
     * @param hashtag the hashtag to add. Cannot be an empty String.
     */
    private void addHashtag(String hashtag) {
        throw new UnsupportedOperationException();
    }

    /**
     * Add a mention.
     *
     * @param mention the user to mention.
     */
    private void addMention(User mention) {
        throw new UnsupportedOperationException();
    }
}
