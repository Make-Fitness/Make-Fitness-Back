package korit.com.make_fitness.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqUpdateCustomerDto {

    @Schema(description = "회원 고유키")
    private int customerId;

    @Schema(description = "멤버십 만료일")
    private LocalDateTime joinDate;

    @Schema(description = "회원 세션 횟수")
    private int classSessionCount;
}
