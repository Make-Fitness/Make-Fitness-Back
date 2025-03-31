package korit.com.make_fitness.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pay {
    private int payId;
    private String uuid;
    private int userId;
    private int managerId;
    private int promotionId;
    private String paymentMethod;
    private int paymentAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Promotion promotion;
    private ClassSubject classSubject;
}
