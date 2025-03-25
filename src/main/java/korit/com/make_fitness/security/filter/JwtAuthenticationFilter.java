package korit.com.make_fitness.security.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.repository.UserRepository;
import korit.com.make_fitness.security.Jwt.JwtUtil;
import korit.com.make_fitness.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    private static final List<String> whitelist = List.of(
            "/api/auth", "/swagger", "/v3/api-docs", "/swagger-ui"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        // ✅ 인증 예외 경로는 바로 통과
        if (isWhitelisted(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // ✅ 나머지는 토큰 인증 처리
        jwtAuthentication(getAccessToken(request));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhitelisted(String uri) {
        return whitelist.stream().anyMatch(uri::startsWith);
    }

    private void jwtAuthentication(String accessToken) {
        if (accessToken == null) return;

        Claims claims = jwtUtil.parseToken(accessToken);
        int userId = Integer.parseInt(claims.getId());

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        PrincipalUser principalUser = PrincipalUser.builder().user(user).build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}
