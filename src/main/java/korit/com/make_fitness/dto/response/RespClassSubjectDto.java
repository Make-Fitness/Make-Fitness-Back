package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespClassSubjectDto {

    private int classSubjectId;
    private String classSubjectName;
}
