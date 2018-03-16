package domain;

import domain.User;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-15T21:04:30")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, GregorianCalendar> postTime;
    public static volatile SingularAttribute<Message, Set> hashtags;
    public static volatile SetAttribute<Message, User> mentions;
    public static volatile SingularAttribute<Message, Integer> id;
    public static volatile SingularAttribute<Message, User> poster;
    public static volatile SingularAttribute<Message, String> content;
    public static volatile SetAttribute<Message, User> likes;

}