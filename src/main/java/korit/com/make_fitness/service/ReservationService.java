package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.dto.response.RespAvailablePromotionDto;
import korit.com.make_fitness.dto.response.RespMyTodayReservationDto;
import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.repository.ClassRepository;
import korit.com.make_fitness.repository.MembershipRepository;
import korit.com.make_fitness.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private ClassRepository classRepository;

    // 수업 예약 처리
    @Transactional(rollbackFor = Exception.class)
    public void reserveClass(ReqReservationDto reqReservationDto) throws AccessDeniedException {
        int result = reservationRepository.insertReservationIfAllowed(reqReservationDto);

        if (result == 0) {
            throw new IllegalStateException("예약 조건을 만족하지 않아 예약에 실패했습니다.");
        }

        classRepository.increaseCustomerReserve(reqReservationDto.getClassId());
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelReservation(int reservationId, int userId) throws AccessDeniedException {

        // 1. 예약 정보 조회
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            throw new IllegalStateException("해당 예약이 존재하지 않습니다.");
        }

        // 2. 소유자 권한 검증
        int ownerUserId = membershipRepository.findUserIdByMembershipId(reservation.getMembershipId());
        if (ownerUserId != userId) {
            throw new AccessDeniedException("해당 예약을 취소할 권한이 없습니다.");
        }

        // 3. 정원 감소 (수업에서 -1)
        classRepository.decreaseCustomerReserve(reservation.getClassId());

        // 4. 세션 복원 (멤버십에서 +1)
        membershipRepository.restoreSessionCount(reservation.getMembershipId());

        // 5. 예약 삭제
        int deleted = reservationRepository.deleteReservationById(reservationId);
        if (deleted == 0) {
            throw new IllegalStateException("예약이 이미 취소되었거나 존재하지 않습니다.");
        }
    }

    // 단건 예약 조회 (권한 검증 포함)
    @Transactional(readOnly = true)
    public Reservation getReservationByIdWithAuthorization(int reservationId, int userId) throws AccessDeniedException {
        Reservation reservation = reservationRepository.findById(reservationId);

        if (reservation == null) {
            throw new IllegalStateException("해당 예약이 존재하지 않습니다.");
        }

        int ownerUserId = membershipRepository.findUserIdByMembershipId(reservation.getMembershipId());

        if (ownerUserId != userId) {
            throw new AccessDeniedException("해당 예약에 접근할 권한이 없습니다.");
        }

        return reservation;
    }

    // 해당 클래스에 이미 예약했는지 확인
    @Transactional(readOnly = true)
    public boolean existsByClassAndMembership(int classId, int membershipId) {
        return reservationRepository.existsByClassAndMembership(classId, membershipId);
    }

    // 멤버십이 예약한 클래스 ID 목록 조회
    @Transactional(readOnly = true)
    public List<Integer> getClassIdListByMembershipId(int membershipId) {
        return reservationRepository.findClassIdListByMembershipId(membershipId);
    }

    // 멤버십의 전체 예약 내역 조회
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByMembershipId(int membershipId) {
        return reservationRepository.findReservationsByMembershipId(membershipId);
    }

    @Transactional(readOnly = true)
    public List<RespAvailablePromotionDto> getAvailablePromotions(int userId) {
        return reservationRepository.findUserPromotionsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<RespMyTodayReservationDto> getTodayReservationsByMembershipId(int membershipId) {

        return reservationRepository.findTodayReservationsByMembershipId(membershipId);
    }

}
