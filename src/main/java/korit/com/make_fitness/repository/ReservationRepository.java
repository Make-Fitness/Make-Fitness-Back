package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.mapper.ClassMapper;
import korit.com.make_fitness.mapper.MembershipMapper;
import korit.com.make_fitness.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationMapper reservationMapper;

    // 조건 만족 시 예약 등록 (성공 시 1, 실패 시 0 반환)
    public int insertReservationIfAllowed(int classId, int membershipId) {
        return reservationMapper.insertReservation(classId, membershipId);
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

    public int deleteReservation(int classId, int membershipId) {
        return reservationMapper.deleteReservation(classId, membershipId);
    }


}
