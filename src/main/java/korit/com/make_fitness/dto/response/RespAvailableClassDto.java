package korit.com.make_fitness.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RespAvailableClassDto {
    private int classId;
    private LocalDateTime classTime;
    private String trainerName;
    private String classSubject;
}
