package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.dto.response.RespAvailablePromotionDto;
import korit.com.make_fitness.dto.response.RespMyTodayReservationDto;
import korit.com.make_fitness.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

    int insertReservation(ReqReservationDto dto);

    boolean existsByClassAndMembership(@Param("classId") int classId, @Param("membershipId") int membershipId);

    List<Reservation> findReservationsByMembershipId(@Param("membershipId") int membershipId);

    List<Integer> findClassIdListByMembershipId(@Param("membershipId") int membershipId);

    Reservation findById(@Param("reservationId") int reservationId);

    int deleteReservationById(@Param("reservationId") int reservationId);

    List<RespAvailablePromotionDto> findAvailablePromotionsByUserId(@Param("userId") int userId);

    List<RespMyTodayReservationDto> findTodayReservationListByUserId(@Param("userId") int userId);

}
