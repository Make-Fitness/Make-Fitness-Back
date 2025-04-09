package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.dto.response.RespAvailablePromotionDto;
import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.dto.response.RespMyTodayReservationDto;
import korit.com.make_fitness.dto.response.RespReservationHistoryDto;
import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationMapper reservationMapper;

    public int insertReservationIfAllowed(ReqReservationDto dto) {
        return reservationMapper.insertReservation(dto);
    }

    public boolean existsByClassAndMembership(int classId, int membershipId) {
        return reservationMapper.existsByClassAndMembership(classId, membershipId);
    }

    public List<Integer> findClassIdListByMembershipId(int membershipId) {
        return reservationMapper.findClassIdListByMembershipId(membershipId);
    }

    public Reservation findById(int reservationId) {
        return reservationMapper.findById(reservationId);
    }

    public int deleteReservationById(int reservationId) {
        return reservationMapper.deleteReservationById(reservationId);
    }

    public List<RespAvailablePromotionDto> findUserPromotionsByUserId(int userId) {
        return reservationMapper.findAvailablePromotionsByUserId(userId);
    }

    public List<RespMyTodayReservationDto> findTodayReservationsByMembershipId(int membershipId) {
        return reservationMapper.findTodayReservationsByMembershipId(membershipId);
    }

    public List<RespClassReservationRow> getAvailableClassesByMembershipId(int membershipId) {
        return reservationMapper.findAvailableClassesByMembershipId(membershipId);
    }

    // 예약 테이블에서 특정 수업에 대한 모든 예약 목록 조회
    public List<Reservation> findReservationsByClassId(int classId) {
        return reservationMapper.findReservationsByClassId(classId);
    }

    // 특정 클래스에 연결된 모든 멤버십 ID 조회
    public List<Integer> findMembershipIdsByClassId(int classId) {
        return reservationMapper.findMembershipIdsByClassId(classId);
    }

    // 지난 예약 목록 조회 (캘린더에 표시하기 위함)
    public List<RespReservationHistoryDto> findReservationHistoryByMembershipId(int membershipId) {
        return reservationMapper.findReservationHistoryByMembershipId(membershipId);
    }
}