package Domain;

/**
 * Role of a user in the application.
 *
 * User means a default user with no added rights.
 * Administrator means being a default user plus having access to administrative functions.
 */
public enum UserRole {
    USER,
    ADMINISTRATOR
}
