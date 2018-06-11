package rest;

import domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import security.AuthenticationFilter;
import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Date;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/authentication")
public class AuthenticationRest
{
    @Inject
    private UserService userService;

    @GET
    @Path("/{email}/{password}")
    @Produces(APPLICATION_JSON)
    public Token authenticateUser(@PathParam("email") String email,
                                     @PathParam("password") String password) {

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

    private User authenticate(String email, String password) throws Exception {
        User user = userService.authenticate(email, password);

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
