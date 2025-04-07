package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.dto.response.RespAvailablePromotionDto;
import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.dto.response.RespMyTodayReservationDto;
import korit.com.make_fitness.dto.response.RespReservationHistoryDto;
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

        membershipRepository.updateSessionCount(reqReservationDto.getMembershipId());
        classRepository.increaseCustomerReserve(reqReservationDto.getClassId());
    }

    // 예약 취소 처리
    @Transactional(rollbackFor = Exception.class)
    public void cancelReservation(int reservationId, int userId) throws AccessDeniedException {
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            throw new IllegalStateException("해당 예약이 존재하지 않습니다.");
        }

        int ownerUserId = membershipRepository.findUserIdByMembershipId(reservation.getMembershipId());
        if (ownerUserId != userId) {
            throw new AccessDeniedException("해당 예약을 취소할 권한이 없습니다.");
        }

        classRepository.decreaseCustomerReserve(reservation.getClassId());
        membershipRepository.restoreSessionCount(reservation.getMembershipId());

        int deleted = reservationRepository.deleteReservationById(reservationId);
        if (deleted == 0) {
            throw new IllegalStateException("예약이 이미 취소되었거나 존재하지 않습니다.");
        }
    }

    // 수업 삭제시 예약 전체 조회용
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByClassId(int classId) {
        return reservationRepository.findReservationsByClassId(classId);
    }

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

    @Transactional(readOnly = true)
    public boolean existsByClassAndMembership(int classId, int membershipId) {
        return reservationRepository.existsByClassAndMembership(classId, membershipId);
    }

    @Transactional(readOnly = true)
    public List<Integer> getClassIdListByMembershipId(int membershipId) {
        return reservationRepository.findClassIdListByMembershipId(membershipId);
    }

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
        if (membershipId <= 0) {
            throw new IllegalArgumentException("유효하지 않은 membershipId 입니다.");
        }

        try {
            List<RespMyTodayReservationDto> reservations = reservationRepository.findTodayReservationsByMembershipId(membershipId);

            if (reservations == null || reservations.isEmpty()) {
                return List.of();
            }

            return reservations;

        } catch (Exception e) {
            throw new RuntimeException("오늘 예약 정보를 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional(readOnly = true)
    public List<RespClassReservationRow> getAvailableClassesByMembershipId(int membershipId) {
        return reservationRepository.getAvailableClassesByMembershipId(membershipId);
    }

    // ✅ 과거 포함한 예약 내역 전체 조회
    @Transactional(readOnly = true)
    public List<RespReservationHistoryDto> getReservationHistoryByMembershipId(int membershipId, int userId) {
        return reservationRepository.findReservationHistoryByMembershipId(membershipId);
    }
}
