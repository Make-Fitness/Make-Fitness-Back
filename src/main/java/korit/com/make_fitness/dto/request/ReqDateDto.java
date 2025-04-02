package korit.com.make_fitness.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqDateDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
