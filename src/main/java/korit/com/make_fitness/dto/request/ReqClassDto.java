package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import korit.com.make_fitness.entity.Class;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ReqClassDto {
    @Schema(description = "사용자 고유키")
    private int managerId;

    @Schema(description = "수업 고유키")
    private int classSubjectId;

    @Schema(description = "수업 시간")
    private LocalDateTime classTime;

    @Schema(description = "시간과 날짜")
    private List<Map<String, Integer>> dateTimes;

}

