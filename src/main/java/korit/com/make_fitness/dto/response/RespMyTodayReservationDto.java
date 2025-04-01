package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespMyTodayReservationDto {

    private LocalDateTime classTime;
}
