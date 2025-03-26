package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqMembershipDto {
    @Schema(description = "멤버십 가입자 고유키")
    private int userId;

    @Schema(description = "프로모션 고유키")
    private int promotionId;

}
