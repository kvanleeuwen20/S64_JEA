package Controller;

import Domain.User;
import Security.Secret;
import Service.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.ServletException;
import javax.xml.ws.Response;
import java.security.Security;
import java.util.Date;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/authentication/login")
    public Token login(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
            // Authenticate the user using the credentials provided
            int id = authenticate(email, password).getID();

            // Issue a token for the user
            String token = issueToken(id);

            // Return the token on the response
            return new Token(token);
    }

    @RequestMapping("authentication/loggedin")
    public int loggedIn(@PathVariable("token") String token) throws Exception {
            System.out.println("TOKEN WHICH WILL BE PARSED:\n" + token);

            // Check if the token was issued by the server and if it's not expired
            // Throw an Exception if the token is invalid
            int id = Integer.parseInt(Jwts.parser().setSigningKey(Secret.SECRET)
                    .parseClaimsJws(token).getBody().getId());

            return id;

    }

    private User authenticate(String email, String password) throws Exception {
        User user = authenticationService.authenticate(email, password);

        if (user == null) {
            throw new Exception("Invalid username and/or password");
        }

        return user;
    }

    private String issueToken(int id) {
        String compactJws = Jwts.builder()
                .setSubject(Integer.toString(id))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 1800000))
                .signWith(SignatureAlgorithm.HS512, Secret.SECRET)
                .compact();

        System.out.println("TOKEN GENERATED:\n" + compactJws);

        return compactJws;
    }

    private class Token {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Token(String token) {
            this.token = token;
        }
    }
}