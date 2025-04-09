package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespLoginDto {
    private String type;
    private String name;
    private String token;

    private String nickname;
    private String roleName;
    private String ph;

    private RespMembershipDto membership;
}
