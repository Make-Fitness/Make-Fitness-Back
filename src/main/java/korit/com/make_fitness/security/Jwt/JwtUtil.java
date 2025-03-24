package korit.com.make_fitness.security.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private Key key;
    private long accessTokenExpire;
    private long refreshTokenExpire;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        accessTokenExpire = 1000l * 60 * 60 * 24;
        refreshTokenExpire = 1000l * 60 * 60 * 24 * 7;
    }

    public String generateToken(String subject, String id, String nickName, String ph, String roleName, Date expires) {
        return Jwts.builder()
                .setSubject(subject)
                .setId(id)
                .claim("nickName", nickName)       // ✅ 닉네임 추가
                .claim("ph", ph) // ✅ 전화번호 추가
                .claim("roleName", roleName)
                .setExpiration(expires)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
