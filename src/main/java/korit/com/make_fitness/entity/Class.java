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
public class Class {
    private int classId;
    private int userId;
    private int classSubjectId;
    private LocalDateTime classTime;
    private int classMaxCustomer;
    private int classCustomerReserve;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private User user;
    private ClassSubject classSubject;
}
