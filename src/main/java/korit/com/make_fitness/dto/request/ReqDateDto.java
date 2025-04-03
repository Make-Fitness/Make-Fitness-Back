package korit.com.make_fitness.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReqDateDto {
    private LocalDate startDate;
    private LocalDate endDate;
}
