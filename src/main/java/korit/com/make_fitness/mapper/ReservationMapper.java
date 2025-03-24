package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    int insertReservation(Reservation reservation);
}
