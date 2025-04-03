package korit.com.make_fitness.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespMemberListDto {

    @Schema(description = "이름")
    private String nickName;

    @Schema(description = "유저고유번호")
    private int userId;

    @Schema(description = "휴대전화번호")
    private String ph;

    @Schema(description = "성별")
    private String gender;

    @Schema(description = "가입 날짜")
    private LocalDateTime createdAt;

    @Schema(description = "회원 구분 (manager → 강사, 그 외는 회원)")
    private String roleName;

    @Schema(description = "프로모션 종류")
    private String promotionName;

    @Schema(description = "프로모션 남은 횟수")
    private int promotionSessionCount;

    @Schema(description = "프로모션 만료일")
    private LocalDateTime expiredDate;
}
