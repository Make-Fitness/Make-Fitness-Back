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
public class Customer {
    private int customerId;
    private int userId;
    private LocalDateTime joinDate;
    private LocalDateTime expireDate;
    private LocalDateTime resetDate;
    private LocalDateTime restDate;
    private String classStatus;
    private int classSessionCount;
    private LocalDateTime updatedAt;
}
