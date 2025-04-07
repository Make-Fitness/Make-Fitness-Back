package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    // 회원가입
    public Optional<User> save(User user) {

        try {
            userMapper.insert(user);

        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(user);
    }

    // 아이디 중복 확인
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userMapper.selectByUsername(username));
    }

    // 로그인
    public Optional<User> findByUserId(int userId) {
        return Optional.ofNullable(userMapper.selectByUserId(userId));
    }

    // 닉네임 조회
    public String findNicknameByUserId(int userId) {
        return userMapper.selectNickNameByUserId(userId);
    }

    // 비밀번호 변경
    public void updatePasswordByUserId(int userId, String password) {
        userMapper.updatePasswordByUserId(userId, password);
    }

    // 권한 변경
    public void updateRoleName(int userId) {
        userMapper.updateUserRoleToCustomer(userId);
    }


}
