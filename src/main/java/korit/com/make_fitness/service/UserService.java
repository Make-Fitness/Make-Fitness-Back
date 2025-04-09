package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqJoinDto;
import korit.com.make_fitness.dto.request.ReqLoginDto;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.exception.DuplicatedValueException;
import korit.com.make_fitness.controller.advice.FieldError;
import korit.com.make_fitness.repository.UserRepository;
import korit.com.make_fitness.security.Jwt.JwtUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // 회원가입
    @Transactional(rollbackFor = Exception.class)
    public User join(ReqJoinDto reqJoinDto) {

        if (duplicatedByUsername(reqJoinDto.getUsername())) {
            throw new DuplicatedValueException(List.of(
                    FieldError.builder()
                            .field("userId")
                            .message("이미 존재하는 아이디 입니다.")
                            .build()
            ));
        }

        User user = reqJoinDto.toUser(passwordEncoder);
        userRepository.save(user);
        return user;
    }

    // 아이디 중복확인
    public boolean duplicatedByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    // 로그인 (사용자 정보 검증 후 JWT 토큰 발급)
    public String login(ReqLoginDto reqLoginDto) {
        User user = userRepository
                .findByUsername(reqLoginDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인해 주세요."));

        if (!passwordEncoder.matches(reqLoginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보가 일치하지 않습니다.");
        }

        Date expires = new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24));  // 24시간

        return jwtUtil.generateToken(
                user.getUsername(),
                Integer.toString(user.getUserId()),
                user.getNickname(),
                user.getPh(),
                user.getRoleName(),
                expires
        );
    }

    // 사용자 정보 조회 (회원 ID로)
    public User getUserByUsername(ReqLoginDto reqLoginDto) throws NotFoundException {
        return userRepository
                .findByUsername(reqLoginDto.getUsername())
                .orElseThrow(() -> new NotFoundException("사용자를 찾지 못했습니다."));
    }

    // 사용자 닉네임 조회
    public String nickname(int userId) {
        return userRepository.findNicknameByUserId(userId);
    }

    // 비밀번호 변경
    public void updatePasswordByUserId(int userId, String password) {
        String newPassword = passwordEncoder.encode(password);
        userRepository.updatePasswordByUserId(userId, newPassword);
    }
}
