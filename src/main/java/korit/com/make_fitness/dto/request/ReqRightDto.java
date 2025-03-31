package korit.com.make_fitness.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqRightDto {

    @Schema(description = "사용자 고유키")
    private int userId;

    @Schema(description = "권한 이름명")
    private String roleName;
}
