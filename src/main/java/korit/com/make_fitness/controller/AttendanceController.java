package korit.com.make_fitness.controller;

import korit.com.make_fitness.dto.request.ReqAttendanceDto;
import korit.com.make_fitness.security.principal.PrincipalUser;
import korit.com.make_fitness.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/makefitness/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;


    // 전화번호로 사용자 존재 여부 및 중복 출석 확인
    @GetMapping("/resolve-user")
    public ResponseEntity<?> resolveUserByPhone(@RequestParam("ph") String ph) {
        Integer userId = attendanceService.findUserIdByPhone(ph);
        if (userId == null) {
            return ResponseEntity.status(404).body("존재하지 않는 전화번호입니다.");
        }

        if (attendanceService.hasAlreadyAttendedToday(userId)) {
            return ResponseEntity.badRequest().body("이미 오늘 출석한 회원입니다.");
        }

        return ResponseEntity.ok().body(
                new ResolveUserResponse(userId)
        );
    }

    // 출석 등록 (userId + 이름, 전화번호 전달)
    @PostMapping("/users/{userId}")
    public ResponseEntity<?> attend(
            @PathVariable int userId,
            @RequestBody ReqAttendanceDto reqAttendanceDto
    ) {
        try {
            attendanceService.attendWithUserId(userId, reqAttendanceDto);
            return ResponseEntity.ok("출석이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 내부 응답 객체 (클래스 바깥으로 뺄 수도 있음)
    record ResolveUserResponse(int userId) {}

    @GetMapping("/check")
    public ResponseEntity<?> checkAttendance(@PathVariable int userId) {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyAttendance(@AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUser().getUserId();
        return ResponseEntity.ok().body(attendanceService.findAttendanceByUserId(userId));
    }                   
}
