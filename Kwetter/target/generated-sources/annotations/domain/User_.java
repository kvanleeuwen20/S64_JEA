package domain;

import domain.Message;
import domain.User;
import domain.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-15T21:04:30")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> website;
    public static volatile SingularAttribute<User, UserRole> role;
    public static volatile SingularAttribute<User, String> bio;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, User> followers;
    public static volatile ListAttribute<User, User> following;
    public static volatile SingularAttribute<User, String> name;
    public static volatile ListAttribute<User, Message> messages;
    public static volatile SingularAttribute<User, String> location;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> profilePicturePath;

}