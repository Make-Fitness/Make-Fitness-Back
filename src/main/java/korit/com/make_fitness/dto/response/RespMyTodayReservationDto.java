package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespMyTodayReservationDto {

    private int reservationId;
    private LocalDateTime classTime;
    private String trainerName;
    private String classSubject;
}
