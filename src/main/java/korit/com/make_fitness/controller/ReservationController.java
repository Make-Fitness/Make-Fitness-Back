package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.security.principal.PrincipalUser;
import korit.com.make_fitness.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/makefitness")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Operation(summary = "수업 예약", description = "멤버십 ID 기준으로 수업 예약 시도 (조건 충족 시만 가능)")
    @PostMapping("/reservation")
    public ResponseEntity<?> reserve(
            @RequestParam int classId,
            @RequestParam int membershipId,
            @AuthenticationPrincipal PrincipalUser principalUser) {

        reservationService.reserveClass(classId, membershipId);
        return ResponseEntity.ok("예약 성공");
    }

    @Operation(summary = "회원 예약 목록 조회", description = "멤버십 ID 기준으로 예약된 수업 목록 조회")
    @GetMapping("/reservation/list/{membershipId}")
    public ResponseEntity<?> getReservations(@PathVariable int membershipId) {
        return ResponseEntity.ok(reservationService.getReservationsByMembershipId(membershipId));
    }

    @Operation(summary = "예약 여부 확인", description = "해당 수업에 대해 이미 예약했는지 여부 반환")
    @GetMapping("/reservation/exist")
    public ResponseEntity<?> checkReservationExists(
            @RequestParam int classId,
            @RequestParam int membershipId) {

        boolean exists = reservationService.existsByClassAndMembership(classId, membershipId);
        return ResponseEntity.ok().body(exists);
    }
}
