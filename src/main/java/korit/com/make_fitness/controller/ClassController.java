package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqClassDto;
import korit.com.make_fitness.dto.response.RespClassListDto;
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

    // ✅ 공통 변환 메서드
    private static RespClassListDto convertToDto(Class c) {
        return RespClassListDto.builder()
                .userId(c.getUserId())
                .classSubjectName(c.getClassSubject().getClassSubjectName())
                .classTime(c.getClassTime().toString())
                .classMaxCustomer(c.getClassMaxCustomer())
                .classCustomerReserve(c.getClassCustomerReserve())
                .remainingSeats(c.getClassMaxCustomer() - c.getClassCustomerReserve())
                .nickname(c.getUser().getNickname())
                .ph(c.getUser().getPh())
                .gender(c.getUser().getGender())
                .build();
    }


    @Operation(summary = "수업 등록", description = "수업 등록 설명")
    @PostMapping("/class")
    public ResponseEntity<?> createClass(
            @RequestBody ReqClassDto reqClassDto,
            @AuthenticationPrincipal PrincipalUser principalUser) throws AccessDeniedException {

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

    @Operation(summary = "수업 목록 조회", description = "전체 수업 + 등록자 + 과목명 포함")
    @GetMapping("/class/list")
    public ResponseEntity<?> getClassList() {
        List<Class> classList = classService.getAllClassWithUserAndSubject();

        List<RespClassListDto> response = classList.stream()
                .map(ClassController::convertToDto)
                .toList();

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "수업명 조회", description = "특정 과목명(Pilates 등)에 해당하는 수업만 조회")
    @GetMapping("/class/subject/{subjectName}")
    public ResponseEntity<?> getClassBySubject(@PathVariable String subjectName) {
        List<Class> classList = classService.getBySubjectName(subjectName);

        List<RespClassListDto> response = classList.stream()
                .map(ClassController::convertToDto)
                .toList();

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "강사이름 수업 조회", description = "강사이름으로 등록한 수업 조회")
    @GetMapping("/class/manager/{nickname}")
    public ResponseEntity<?> getClassByManager(@PathVariable String nickname) {
        List<Class> classList = classService.getByManagerNickname(nickname);

        List<RespClassListDto> response = classList.stream()
                .map(ClassController::convertToDto)
                .toList();

        return ResponseEntity.ok().body(response);
    }
}
