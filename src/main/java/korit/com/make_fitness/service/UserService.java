package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqJoinDto;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public User join(ReqJoinDto reqJoinDto) {
        User user = reqJoinDto.toUser(passwordEncoder);
        userRepository.save(user);
        return user;
    }


}
