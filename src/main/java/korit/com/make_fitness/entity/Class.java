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
    private int class_id;
    private int customerId;
    private int managerId;
    private int class_subject_id;
    private LocalDateTime classTime;
    private int classMaxCustomer;
    private int classCustomerReserve;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
