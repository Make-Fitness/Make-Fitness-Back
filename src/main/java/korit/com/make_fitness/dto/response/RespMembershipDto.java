package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespMembershipDto {

    private int membershipId;
    private int promotionId;
    private int promotionSessionCount;
    private int promotionSessionTime;
    private LocalDateTime expiredDate;
}
