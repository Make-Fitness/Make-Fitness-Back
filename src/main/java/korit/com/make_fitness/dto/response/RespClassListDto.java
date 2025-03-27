package korit.com.make_fitness.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespClassListDto {
    private int classId;
    private int userId;
    private String classSubjectName;
    private String classTime;
    private int classMaxCustomer;
    private int classCustomerReserve;
    private String nickname;
    private String ph;
    private String gender;

    private int remainingSeats;
}