package Service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private UserRepository userRepository;

    /**
     * Authenticates a user by the given email and login.
     *
     * @param email the email address
     * @param password the password
     * @return the user if one is found, else null.
     */
    public domain.User authenticate(String email, String password) {
        return userRepository (email, password);
    }
}