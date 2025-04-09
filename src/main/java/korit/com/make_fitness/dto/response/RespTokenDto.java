package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespTokenDto {
    private String type;
    private String name;
    private String token;


}
