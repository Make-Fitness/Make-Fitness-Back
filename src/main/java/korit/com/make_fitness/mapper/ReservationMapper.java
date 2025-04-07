// ReservationMapper.java
package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.dto.response.RespAvailablePromotionDto;
import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.dto.response.RespMyTodayReservationDto;
import korit.com.make_fitness.dto.response.RespReservationHistoryDto;
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

    List<RespMyTodayReservationDto> findTodayReservationsByMembershipId(int membershipId);

    List<RespClassReservationRow> findAvailableClassesByMembershipId(int membershipId);

    // 예약된 유저의 membership_id 리스트 조회
    List<Integer> findMembershipIdsByClassId(@Param("classId") int classId);

    // 클래스에 연결된 예약 전체 조회
    List<Reservation> findReservationsByClassId(@Param("classId") int classId);

    // 과거에 내가 들었던 수업 목록 (날짜 기준 내림차순)
    List<RespReservationHistoryDto> findReservationHistoryByMembershipId(@Param("membershipId") int membershipId);}