package korit.com.make_fitness.security.principal;

import korit.com.make_fitness.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class PrincipalUser implements UserDetails{
    private User user;
    private Map<String, Object> attributes;
    private String name;

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRoleName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountExpired() == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountLocked() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsExpired() == 1;
    }

    @Override
    public boolean isEnabled() {
        return user.getAccountEnabled() == 1;
    }
}