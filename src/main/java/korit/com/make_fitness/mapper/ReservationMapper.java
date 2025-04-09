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

    // 수업 예약 등록
    int insertReservation(ReqReservationDto dto);

    // 특정 클래스-멤버십 조합의 예약 존재 여부 확인
    boolean existsByClassAndMembership(@Param("classId") int classId, @Param("membershipId") int membershipId);

    // 특정 멤버십의 전체 예약 내역 조회
    List<Reservation> findReservationsByMembershipId(@Param("membershipId") int membershipId);

    // 특정 멤버십으로 예약된 클래스 ID 목록 조회
    List<Integer> findClassIdListByMembershipId(@Param("membershipId") int membershipId);

    // 예약 ID로 예약 단건 조회
    Reservation findById(@Param("reservationId") int reservationId);

    // 예약 ID로 예약 삭제
    int deleteReservationById(@Param("reservationId") int reservationId);

    // 특정 유저가 보유한 사용 가능한 프로모션(멤버십) 목록 조회
    List<RespAvailablePromotionDto> findAvailablePromotionsByUserId(@Param("userId") int userId);

    // 특정 멤버십으로 오늘 예약한 클래스 목록 조회
    List<RespMyTodayReservationDto> findTodayReservationsByMembershipId(int membershipId);

    // 특정 멤버십으로 예약 가능한 클래스 목록 조회 (프론트용)
    List<RespClassReservationRow> findAvailableClassesByMembershipId(int membershipId);

    // 특정 클래스에 예약한 멤버십 ID 목록 조회
    List<Integer> findMembershipIdsByClassId(@Param("classId") int classId);

    // 특정 클래스에 연결된 모든 예약 목록 조회
    List<Reservation> findReservationsByClassId(@Param("classId") int classId);

    // 과거 수강한 수업 이력 조회 (날짜 기준 내림차순)
    List<RespReservationHistoryDto> findReservationHistoryByMembershipId(@Param("membershipId") int membershipId);
}
