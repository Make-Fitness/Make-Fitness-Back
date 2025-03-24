package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqReservationDto {

    @Schema(description = "회원 아이디")
    private int customerId;

    @Schema(description = "수업 아이디")
    private int classId;

    @Schema(description = "예약 날짜 및 시간")
    private LocalDateTime bookingDateTime;
}
