package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {

    // 수업 예약
    int insertReservation(Reservation reservation);

    // 수업 전체 조회
    List<Reservation> findAllByCustomerId(int customerId);


}
