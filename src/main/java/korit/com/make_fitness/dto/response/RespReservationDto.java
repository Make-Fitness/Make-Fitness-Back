package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespReservationDto {

    private int reservationId;
    private int classId;
    private LocalDateTime bookingDAteTime;
    private String bookingStatus;
}
