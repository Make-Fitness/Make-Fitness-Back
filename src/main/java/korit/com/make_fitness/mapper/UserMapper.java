package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    // 비밀번호 변경
    int updatePasswordByUserId(@Param("userId") int userId, @Param("password") String password);

    // 권한 변경
    int updateUserRoleToCustomer(@Param("userId") int userId);

}