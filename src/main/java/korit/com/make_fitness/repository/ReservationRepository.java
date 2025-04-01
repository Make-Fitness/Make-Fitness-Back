package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.dto.response.RespAvailablePromotionDto;
import korit.com.make_fitness.dto.response.RespMyTodayReservationDto;
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

    public List<Reservation> findReservationsByMembershipId(int membershipId) {
        return reservationMapper.findReservationsByMembershipId(membershipId);
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

}
