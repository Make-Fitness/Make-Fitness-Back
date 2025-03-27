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
public class Promotion {

    private int promotionId;
    private String promotionName;
    private int classSubjectId;
    private int price;
    private int promotionSessionCount;
    private int promotionSessionTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
