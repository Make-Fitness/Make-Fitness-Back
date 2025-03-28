package korit.com.make_fitness.security.oAuth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.security.Jwt.JwtUtil;
import korit.com.make_fitness.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value(value = "${react.server.protocol}")
    private String protocol;
    @Value(value = "${react.server.host}")
    private String host;
    @Value(value = "${react.server.port}")
    private int port;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        User user = principalUser.getUser();

        Date expires = new Date(new Date().getTime() + (1000l * 60 * 60 * 7));
        String accessToken = jwtUtil.generateToken(user.getUsername(), Integer.toString(user.getUserId()), user.getNickname(), user.getPh(), user.getRoleName(), expires);
        response.sendRedirect(String.format("%s://%s:%d/auth/login/oauth2?accessToken=%s", protocol, host, port, accessToken));


    }
}