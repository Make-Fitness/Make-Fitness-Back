package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import korit.com.make_fitness.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Schema(description = "회원가입 정보 DTO")
public class ReqJoinDto {
    @Schema(description = "사용자 아이디")
    private String username;
    
    @Schema(description = "사용자 비밀번호")
    private String password;
    
    @Schema(description = "사용자 이름")
    private String nickname;
    
    @Schema(description = "휴대전화번호")
    private String ph;
    
    @Schema(description = "이메일")
    private String email;
    
    @Schema(description = "성별")
    private String gender;

    public User toUser(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .ph(ph)
                .gender(gender)
                .build();
    }
}
