package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespCustomerDto {

    private int customerId;
    private LocalDateTime joinDate;
    private LocalDateTime expireDate;
    private LocalDateTime resetDate;
    private LocalDateTime restDate;
    private String classStatus;
    private int classSessionCount;
}
