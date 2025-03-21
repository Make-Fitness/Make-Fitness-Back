package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqCustomerDto {
    @Schema(description = "고유키")
    private int userId;

    @Schema(description = "수업 종류")
    private String classStatus;
}
