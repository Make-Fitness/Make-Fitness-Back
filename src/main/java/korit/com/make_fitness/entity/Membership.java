package korit.com.make_fitness.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Membership {

    private int membershipId;
    private int userId;
    private int promotionId;
    private int promotionSessionCount;
    private int promotionSessionTime;
    private LocalDateTime expiredDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
