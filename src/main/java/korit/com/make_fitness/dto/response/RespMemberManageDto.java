package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespMemberManageDto {
    private String nickName;
    private String ph;
    private String promotionName;
    private int promotionSessionCount;
    private LocalDateTime expiredDate;
}
