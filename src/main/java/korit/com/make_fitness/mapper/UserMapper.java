package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 회원가입
    int insert(User user);

    // 아이디 중복 확인
    User selectByUsername(String username);

    // 로그인
    User selectByUserId(int userId);

    // 닉네임 조회
    String selectNickNameByUserId(int userId);
}
