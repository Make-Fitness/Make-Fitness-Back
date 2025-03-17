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
    private int paymentId;
    private int customerId;
    private int classId;
    private String paymentMethod;
    private int paymentAmount;
    private int refund;
    private int renewal;
    private int monthly;
    private String promotion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
