package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import korit.com.make_fitness.entity.Pay;
import lombok.Data;

@Data
public class ReqPayDto {

    @Schema(description = "멤버십 가입자 uuid")
    private String uuid;

    @Schema(description = "멤버십 가입자 고유키")
    private int userId;

    @Schema(description = "트레이너 고유키")
    private int managerId;

    @Schema(description = "멤버십 프로모션 고유키")
    private int promotionId;

    @Schema(description = "멤버십 프로모션 결제수단")
    private String paymentMethod;


    public Pay toPay() {
        return Pay.builder()
                .uuid(uuid)
                .userId(userId)
                .managerId(managerId)
                .promotionId(promotionId)
                .paymentMethod(paymentMethod)
                .build();
    }
}
