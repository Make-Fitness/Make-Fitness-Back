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
    
    @Schema(description = "휴대전화번호")
    private String ph;
    
    @Schema(description = "프로모션 종류")
    private String promotionName;
    
    @Schema(description = "프로모션 남은 횟수")
    private int promotionSessionCount;
    
    @Schema(description = "프로모션 만료일")
    private LocalDateTime expiredDate;
}
