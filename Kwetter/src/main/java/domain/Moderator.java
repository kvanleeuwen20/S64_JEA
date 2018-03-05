package domain;

import java.util.ArrayList;

public class Moderator extends User {
    /**
     * Upgrade a user to a moderator.
     *
     * @param user the user to upgrade.
     */
    public Moderator(User user) {
        super(user);
    }
}
