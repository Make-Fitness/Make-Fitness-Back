package korit.com.make_fitness.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RespManagerDto {

    @Schema(description = "트레이너 고유키")
    private int managerId;
    
    @Schema(description = "이름")
    private String nickname;
    
    @Schema(description = "성별")
    private String gender;
    
    @Schema(description = "휴대전화번호")
    private String ph;
    
    @Schema(description = "수업 건수")
    private int classMemberCount;
    
    @Schema(description = "세션 수")
    private int classSessionCount;

    @Schema(description = "년월")
    private LocalDate classTime;
}
