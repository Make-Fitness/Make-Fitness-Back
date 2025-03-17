package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqLoginDto {

    @Schema(description = "사용자 아이디")
    private String username;

    @Schema(description = "사용자 비밀번호")
    private String password;
}
