package korit.com.make_fitness.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReqDateDto {
    private LocalDate startDate;
    private LocalDate endDate;
}
