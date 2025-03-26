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

    @Schema(description = "트레이너 id")
    private int userId;

    @Schema(description = "수업 고유키")
    private int classSubjectId;

    @Schema(description = "수업 시간")
    private LocalDateTime classTime;

    @Schema(description = "최대 수업 인원")
    private int classMaxCustomer;

    @Schema(description = "최대 수업 인원")
    private int classCustomerReserve;

}

