package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    // 수업 예약
    int insertReservation(Reservation reservation);


}
