package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthenticationController {
    public int checkLoggedIn(String token) throws Exception {
        System.out.println("TOKEN WHICH WILL BE PARSED:\n" + token);
        String uri = "http://localhost:8080/authentication/loggedin/";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri + token, String.class);

        int id = Integer.parseInt(result);

        return id;
    }
}
