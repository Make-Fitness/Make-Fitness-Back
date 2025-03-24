package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import korit.com.make_fitness.entity.Class;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqClassDto {
    
    @Schema(description = "트레이너 고유키")
    private int userId;

    @Schema(description = "수업 고유키")
    private int classSubjectId;

    @Schema(description = "수업 시간")
    private LocalDateTime classTime;
    
    @Schema(description = "수업 최대 인원 수")
    private int classMaxCustomer;
    
    public Class toClass() {
        return Class.builder()
                .managerId(userId)
                .classSubjectId(classSubjectId)
                .classTime(classTime)
                .classMaxCustomer(classMaxCustomer)
                .build();
    } 
}
