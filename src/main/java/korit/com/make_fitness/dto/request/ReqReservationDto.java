package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqReservationDto {

    @Schema(description = "예약 아이디 (자동 생성)")
    private int reservationId;

    @Schema(description = "수업 아이디")
    private int classId;

    @Schema(description = "멤버십 아이디")
    private int membershipId;
}
