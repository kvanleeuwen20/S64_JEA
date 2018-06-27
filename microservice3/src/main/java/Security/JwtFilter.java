package Security;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        } else {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header");
            }

            final String token = authHeader.substring(7);

            int id = 0;

            try {
                id = this.checkLoggedIn(token);
                request.setAttribute("id", id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            chain.doFilter(req, res);
        }
    }

    private int checkLoggedIn(String token) throws Exception {
        System.out.println("TOKEN WHICH WILL BE PARSED:\n" + token);
        String uri = "http://localhost:8080/authentication/loggedin/";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri + token, String.class);

        int id = Integer.parseInt(result);

        return id;
    }
}
