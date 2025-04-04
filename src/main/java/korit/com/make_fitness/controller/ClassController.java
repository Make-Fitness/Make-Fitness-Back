package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqClassDto;
import korit.com.make_fitness.dto.response.RespClassListDto;
import korit.com.make_fitness.dto.response.RespClassSubjectDto;
import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.security.principal.PrincipalUser;
import korit.com.make_fitness.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/makefitness")
public class ClassController {

    @Autowired
    private ClassService classService;

    @Operation(summary = "수업 등록", description = "수업 등록 설명")
    @PostMapping("/classes")
    public ResponseEntity<?> createClass(
            @RequestBody ReqClassDto reqClassDto,
            @AuthenticationPrincipal PrincipalUser principalUser) throws AccessDeniedException {

        if (principalUser == null) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }
        User user = principalUser.getUser();

        Class classEntity = Class.builder()
                .userId(user.getUserId())
                .classSubjectId(reqClassDto.getClassSubjectId())
                .classTime(reqClassDto.getClassTime())
                .classMaxCustomer(reqClassDto.getClassMaxCustomer())
                .classCustomerReserve(reqClassDto.getClassCustomerReserve())
                .build();

        return ResponseEntity.ok().body(classService.createClass(classEntity, user));
    }

    @Operation(summary = "수업 삭제", description = "수업을 삭제하고 예약된 유저들의 멤버십 카운트를 증가시킴")
    @DeleteMapping("/classes/{classId}")
    public ResponseEntity<?> deleteClass(
            @PathVariable int classId,
            @AuthenticationPrincipal PrincipalUser principalUser) throws AccessDeniedException {

        User user = principalUser.getUser();
        classService.deleteClass(classId, user);

        return ResponseEntity.ok("수업이 삭제되었고 예약된 멤버십 카운트가 증가되었습니다.");
    }

    @Operation(summary = "수업 목록 조회", description = "전체 or 조건(subject, manager) 조회")
    @GetMapping("/classes")
    public ResponseEntity<?> getFilteredClassList(
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String manager) {

        List<RespClassListDto> classList = classService.getFilteredClassList(subject, manager);
        return ResponseEntity.ok().body(classList);
    }

    @Operation(summary = "수업 단건 조회", description = "classId 기준으로 특정 수업 상세 조회")
    @GetMapping("/classes/{classId}")
    public ResponseEntity<?> getClassById(@PathVariable int classId) {
        return ResponseEntity.ok().body(classService.getClassById(classId));
    }

    @Operation(summary = "등록된 수업 + 예약자 명단 조회", description = "트레이너가 등록한 수업 목록과 예약자 정보를 확인합니다.")
    @GetMapping("/classes/with-reservations")
    public ResponseEntity<?> getClassWithReservations(
            @AuthenticationPrincipal PrincipalUser principalUser) {

        int managerId = principalUser.getUser().getUserId();
        return ResponseEntity.ok(classService.getClassWithReservations(managerId));
    }

    @Operation(summary = "트레이너 담당 수업 주제 반환", description = "로그인된 트레이너의 classSubjectId 및 이름을 반환합니다.")
    @GetMapping("/subject/me")
    public ResponseEntity<?> getTrainerSubject(
            @AuthenticationPrincipal PrincipalUser principalUser
    ) throws AccessDeniedException {
        User user = principalUser.getUser();
        RespClassSubjectDto subjectDto = classService.getTrainerClassSubject(user);
        return ResponseEntity.ok(subjectDto);
    }

    @Operation(summary = "해당 날짜의 등록된 수업 시간 조회", description = "트레이너 기준으로 특정 날짜에 등록된 시간 목록 반환")
    @GetMapping("/classes/registered-times")
    public ResponseEntity<?> getRegisteredTimesByDate(
            @RequestParam String date,
            @AuthenticationPrincipal PrincipalUser principalUser
    ) throws AccessDeniedException {
        User user = principalUser.getUser();
        List<Integer> registeredTimes = classService.getRegisteredTimesByDate(user, date);
        return ResponseEntity.ok(registeredTimes);
    }
}
