package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqUpdateMembershipDto {

    @Schema(description = "멤버십 회원 고유키")
    private int membershipId;

}
