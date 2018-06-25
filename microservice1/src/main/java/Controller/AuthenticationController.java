package Controller;

import Security.Secret;
import Service.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/authentication/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) throws ServletException {
        System.out.println(email);
        System.out.println(password);
        try {

            // Authenticate the user using the credentials provided
            int id = authenticate(email, password).getID();

            // Issue a token for the user
            String token = issueToken(id);

            // Return the token on the response
            return new Token(token);
            //return Response.ok(token, MediaType.TEXT_PLAIN).build();

        } catch (Exception e) {
            return null;
            // return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private domain.User authenticate(String email, String password) throws Exception {
        domain.User user = authenticationService.authenticate(email, password);

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
                .signWith(SignatureAlgorithm.HS512, AuthenticationFilter.secret)
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