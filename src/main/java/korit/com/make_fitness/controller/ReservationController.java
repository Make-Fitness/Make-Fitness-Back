package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.security.principal.PrincipalUser;
import korit.com.make_fitness.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/makefitness")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Operation(summary = "수업 예약", description = "클래스와 멤버십 정보를 기반으로 수업을 예약")
    @PostMapping("/reservation")
    public ResponseEntity<?> reserve(
            @RequestBody ReqReservationDto dto,
            @AuthenticationPrincipal PrincipalUser principalUser) throws AccessDeniedException {
        reservationService.reserveClass(dto);
        return ResponseEntity.ok("예약 성공");
    }

    @Operation(summary = "수업 예약 취소", description = "reservationId 기준 예약 취소 (정원 감소 + 세션 복원)")
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<?> cancelReservation(
            @PathVariable int reservationId,
            @AuthenticationPrincipal PrincipalUser principalUser) throws AccessDeniedException {
        int userId = principalUser.getUser().getUserId();
        reservationService.cancelReservation(reservationId, userId);
        return ResponseEntity.ok("예약 취소 완료");
    }

    @Operation(summary = "예약 단건 조회", description = "reservationId 기준으로 예약 상세 조회")
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<?> getReservationDetail(
            @PathVariable int reservationId,
            @AuthenticationPrincipal PrincipalUser principalUser) throws AccessDeniedException {
        int userId = principalUser.getUser().getUserId();
        return ResponseEntity.ok(reservationService.getReservationByIdWithAuthorization(reservationId, userId));
    }

    @Operation(summary = "예약 여부 확인", description = "해당 수업에 대해 이미 예약했는지 여부 반환")
    @GetMapping("/reservation/exist")
    public ResponseEntity<?> checkReservationExists(
            @RequestParam int classId,
            @RequestParam int membershipId) {
        boolean exists = reservationService.existsByClassAndMembership(classId, membershipId);
        return ResponseEntity.ok(exists);
    }

    @Operation(summary = "예약 가능한 프로모션 목록", description = "현재 로그인한 사용자가 예약 가능한 프로모션 목록을 반환")
    @GetMapping("/reservations/available-promotions")
    public ResponseEntity<?> getAvailablePromotions(
            @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUser().getUserId();
        return ResponseEntity.ok(reservationService.getAvailablePromotions(userId));
    }

    @Operation(summary = "오늘 내 수업 예약 목록", description = "오늘 날짜 기준으로 예약된 수업의 시간 리스트 반환")
    @GetMapping("/reservation/today")
    public ResponseEntity<?> getTodayReservationsByMembership(@RequestParam int membershipId) {
        return ResponseEntity.ok(reservationService.getTodayReservationsByMembershipId(membershipId));
    }

    @Operation(summary = "예약 가능한 수업 조회", description = "오늘 날짜 기준, 예약 가능한 수업 리스트를 조회합니다.")
    @GetMapping("/classes/reservable")
    public ResponseEntity<?> getReservableClasses(@RequestParam int membershipId) {
        return ResponseEntity.ok(reservationService.getAvailableClassesByMembershipId(membershipId));
    }

    @Operation(summary = "과거 수업 예약 이력", description = "멤버십 ID를 기준으로 오늘 이전에 수강한 수업 목록을 조회")
    @GetMapping("/reservation/history")
    public ResponseEntity<?> getReservationHistory(
            @RequestParam int membershipId,
            @AuthenticationPrincipal PrincipalUser principalUser) throws AccessDeniedException {
        int userId = principalUser.getUser().getUserId();
        return ResponseEntity.ok(reservationService.getReservationHistoryByMembershipId(membershipId, userId));
    }
}
