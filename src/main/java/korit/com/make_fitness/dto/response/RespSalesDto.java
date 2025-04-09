package korit.com.make_fitness.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RespSalesDto {

    @Schema(description = "날짜")
    private LocalDate date;
    
    @Schema(description = "총 매출")
    private int totalAmount;
    
    @Schema(description = "PT 매출")
    private int ptTotalAmount;
    
    @Schema(description = "필라테스 매출")
    private int pltTotalAmount;
    
    @Schema(description = "헬스 매출")
    private int htTotalAmount;
}
