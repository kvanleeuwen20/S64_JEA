package dto;

import domain.Message;
import domain.User;

import java.util.GregorianCalendar;
import java.util.Set;

public class MessageDTO {
    private int id;

    private String content;

    private Set<String> hashtags;

    private Set<User> likes;

    private Set<User> mentions;

    private User poster;

    private GregorianCalendar postTime;

    public MessageDTO(int id, String content, Set<String> hashtags, Set<User> likes, Set<User> mentions, User poster, GregorianCalendar postTime) {
        this.id = id;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.mentions = mentions;
        this.poster = poster;
        this.postTime = postTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<String> hashtags) {
        this.hashtags = hashtags;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<User> getMentions() {
        return mentions;
    }

    public void setMentions(Set<User> mentions) {
        this.mentions = mentions;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public GregorianCalendar getPostTime() {
        return postTime;
    }

    public void setPostTime(GregorianCalendar postTime) {
        this.postTime = postTime;
    }

    public static MessageDTO fromMessage(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getHashtags(),
                message.getLikes(),
                message.getMentions(),
                message.getPoster(),
                message.getPostTime()
        );
    }
}
