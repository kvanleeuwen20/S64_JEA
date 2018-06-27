package Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HashTag {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String tag;

    public HashTag() {

    }

    public HashTag(String tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null.");
        }

        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null.");
        }

        this.tag = tag;
    }
}
