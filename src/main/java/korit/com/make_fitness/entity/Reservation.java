package korit.com.make_fitness.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private int reservationId;
    private int customerId;
    private int classRegisterId;
    private int managerId;
    private String nickname;
    private LocalDateTime bookingDate;
    private LocalDateTime bookingTime;
    private String classList;
    private String teacher;
    private String bookingStatus;
    private int mySessionCount;
    private int openSlots;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
