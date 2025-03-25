package korit.com.make_fitness.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassDto {
    private int managerId;
    private int classSubjectId;
    private LocalDateTime classTime;
    private int classMaxCustomer;
    private int classCustomerReserve;
}
