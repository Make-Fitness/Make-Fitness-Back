package korit.com.make_fitness.service;

import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.repository.ClassRepository;
import korit.com.make_fitness.repository.MembershipRepository;
import korit.com.make_fitness.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private ClassRepository classRepository;

     // 수업 예약 처리 (조건 만족 시 삽입 + 세션 차감 + 정원 증가)
    @Transactional(rollbackFor = Exception.class)
    public void reserveClass(int classId, int membershipId) {
        int result = reservationRepository.insertReservationIfAllowed(classId, membershipId);

        if (result == 0) {
            throw new IllegalStateException("예약 조건을 만족하지 않아 예약에 실패했습니다.");
        }

        // 예약 인원수 증가
        classRepository.increaseCustomerReserve(classId);
    }

     // 이미 예약했는지 확인
    @Transactional(readOnly = true)
    public boolean existsByClassAndMembership(int classId, int membershipId) {
        return reservationRepository.existsByClassAndMembership(classId, membershipId);
    }

     // 해당 멤버십으로 예약된 수업 ID 목록 조회
    @Transactional(readOnly = true)
    public List<Integer> getClassIdListByMembershipId(int membershipId) {
        return reservationRepository.findClassIdListByMembershipId(membershipId);
    }

     // 해당 멤버십의 전체 예약 내역 조회
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByMembershipId(int membershipId) {
        return reservationRepository.findReservationsByMembershipId(membershipId);
    }
}
