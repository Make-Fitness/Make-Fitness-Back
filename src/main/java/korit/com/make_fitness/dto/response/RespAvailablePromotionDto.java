package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespAvailablePromotionDto {

    private int membershipId;
    private String promotionName;
    private String trainerName;
    private int remainingSessionCount;
    private LocalDateTime expiredDate;
}
