package korit.com.make_fitness.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespReservationHistoryDto {
    private int reservationId;
    private int classId;
    private String classSubject;
    private String trainerName;
    private LocalDateTime classTime;
    private boolean attended;  // 출석 여부
    private boolean canceled;  // 취소 여부
}