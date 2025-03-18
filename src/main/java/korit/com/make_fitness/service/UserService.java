package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqJoinDto;
import korit.com.make_fitness.dto.request.ReqLoginDto;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.exception.DuplicatedValueException;
import korit.com.make_fitness.controller.advice.FieldError;
import korit.com.make_fitness.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 회원가입(아이디 중복확인 포함)
    @Transactional(rollbackFor = Exception.class)
    public User join(ReqJoinDto reqJoinDto) {
        if(duplicatedByUsername(reqJoinDto.getUsername())) {
            throw new DuplicatedValueException(List.of(FieldError.builder()
                    .field("username")
                    .message("이미 존재하는 사용자이름입니다.")
                    .build()));
        }

        User user = reqJoinDto.toUser(passwordEncoder);
        userRepository.save(user);
        return user;
    }

    // 아이디 중복확인
    public boolean duplicatedByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    // 로그인
    public User getUserByUsername(ReqLoginDto reqLoginDto) throws NotFoundException {
        User user = userRepository
                .findByUsername(reqLoginDto.getUsername())
                .orElseThrow(() -> new NotFoundException("사용자를 찾지 못했습니다."));

        if(!passwordEncoder.matches(reqLoginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요.");
        }

        return user;
    }

}
