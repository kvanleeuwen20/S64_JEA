package dto;

import domain.HashTag;
import domain.Message;
import domain.User;

import java.util.GregorianCalendar;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageDTO {
    private int id;

    private String content;

    private Set<HashTagDTO> hashTags;

    private Set<UserDTO> likes;

    private Set<UserDTO> mentions;

    private UserDTO poster;

    private GregorianCalendar postTime;

    public MessageDTO() {

    }

    public MessageDTO(int id, String content, Set<HashTagDTO> hashTags, Set<UserDTO> likes, Set<UserDTO> mentions, UserDTO poster, GregorianCalendar postTime) {
        this.id = id;
        this.content = content;
        this.hashTags = hashTags;
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

    public Set<HashTagDTO> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTagDTO> hashTags) {
        this.hashTags = hashTags;
    }

    public Set<UserDTO> getLikes() {
        return likes;
    }

    public void setLikes(Set<UserDTO> likes) {
        this.likes = likes;
    }

    public Set<UserDTO> getMentions() {
        return mentions;
    }

    public void setMentions(Set<UserDTO> mentions) {
        this.mentions = mentions;
    }

    public UserDTO getPoster() {
        return poster;
    }

    public void setPoster(UserDTO poster) {
        this.poster = poster;
    }

    public GregorianCalendar getPostTime() {
        return postTime;
    }

    public void setPostTime(GregorianCalendar postTime) {
        this.postTime = postTime;
    }

    public static MessageDTO fromMessage(Message message) {
        Set<HashTagDTO> hashTags = message.getHashTags().stream().map(HashTagDTO::fromHashTag).collect(Collectors.toSet());
        Set<UserDTO> likes = message.getLikes().stream().map(UserDTO::fromUser).collect(Collectors.toSet());
        Set<UserDTO> mentions = message.getMentions().stream().map(UserDTO::fromUser).collect(Collectors.toSet());

        return new MessageDTO(
                message.getId(),
                message.getContent(),
                hashTags,
                likes,
                mentions,
                UserDTO.fromUser(message.getPoster()),
                message.getPostTime()
        );
    }
}
