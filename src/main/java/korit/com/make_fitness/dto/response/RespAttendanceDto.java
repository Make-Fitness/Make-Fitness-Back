package korit.com.make_fitness.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RespAttendanceDto {
    private int userId;
    private LocalDate attendDate;
}
