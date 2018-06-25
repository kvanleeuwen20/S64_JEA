package Service;

import Domain.User;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticates a user by the given email and login.
     *
     * @param email the email address
     * @param password the password
     * @return the user if one is found, else null.
     */
    public User authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}