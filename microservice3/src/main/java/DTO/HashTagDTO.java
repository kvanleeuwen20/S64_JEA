package DTO;

import Domain.HashTag;

public class HashTagDTO {
    private int id;

    private String tag;

    public HashTagDTO() {

    }

    public HashTagDTO(int id, String tag) {
        this.id = id;
        this.tag= tag;
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static HashTagDTO fromHashTag(HashTag hashTag) {
        return new HashTagDTO(
                hashTag.getId(),
                hashTag.getTag()
        );
    }
}
