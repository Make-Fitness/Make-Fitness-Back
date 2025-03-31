package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RespClassReservationDto {
    private Long classId;
    private LocalDateTime classTime;
    private String subject;
    private int maxCustomer;
    private int currentCustomer;
    private List<String> reservedMembers;
}
