package korit.com.make_fitness.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RespClassReservationRow {
    private Long classId;
    private LocalDateTime classTime;
    private String subject;             // class_subject_name
    private int maxCustomer;            // class_max_customer
    private int currentCustomer;        // class_customer_reserve
    private String reservedMember;      // 유저 닉네임 1명 (nullable)
}
