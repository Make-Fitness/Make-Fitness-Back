package korit.com.make_fitness.security.oAuth2;

import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.repository.UserRepository;
import korit.com.make_fitness.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DefaultOAuth2UserService defaultOAuth2UserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String email = null;
        String oauth2Name = null;
        String oauth2Provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = getDefaultOAuth2User(userRequest).getAttributes();
        if(oauth2Provider.equalsIgnoreCase("naver")) {
            attributes = (Map<String, Object>) attributes.get("response");
            oauth2Name = (String) attributes.get("email");
        }
        if(oauth2Provider.equalsIgnoreCase("google")) {
            oauth2Name = (String) attributes.get("sub");
            email = (String) attributes.get("email");
        }

        final String USERNAME = oauth2Provider + "_" + oauth2Name;
        final String FINAL_EMAIL = email;
        final String FINAL_OAUTH2NAME = oauth2Name;

        User user = userRepository
                .findByUsername(USERNAME)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .username(USERNAME)
                            .nickname(USERNAME)
                            .email(FINAL_EMAIL)
                            .oAuth2Name(FINAL_OAUTH2NAME)
                            .oAuth2Provider(oauth2Provider)
                            .accountExpired(1)
                            .accountLocked(1)
                            .credentialsExpired(1)
                            .accountEnabled(1)
                            .build();

                    userRepository.save(newUser);
                    return userRepository.findByUsername(USERNAME).get();

                });

        return PrincipalUser.builder()
                .user(user)
                .name(oauth2Name)
                .attributes(attributes)
                .build();
    }

    private OAuth2User getDefaultOAuth2User(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return defaultOAuth2UserService.loadUser(userRequest);
    }
}
