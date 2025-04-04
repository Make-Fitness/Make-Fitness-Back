package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqDateDto;
import korit.com.make_fitness.dto.request.ReqRightDto;
import korit.com.make_fitness.dto.response.RespMemberListDto;
import korit.com.make_fitness.dto.response.RespSalesDto;
import korit.com.make_fitness.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/makefitness/admin")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @Operation(summary = "트레이너 삭제", description = "트레이너 삭제 설명")
    @DeleteMapping("/manager/{userId}")
    public ResponseEntity<?> removeManager(@RequestBody ReqRightDto reqRightDto) {
        masterService.removeManager(reqRightDto.getRoleName(), reqRightDto.getUserId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 권한 변경", description = "회원 번호에 따른 권한 변경")
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<?> changeRoleName(@PathVariable int userId, @RequestBody ReqRightDto reqRightDto) {
        masterService.changeRoleName(userId, reqRightDto.getRoleName());
        return ResponseEntity.ok().build(); // 변경 성공 여부만 응답
    }


    @Operation(summary = "트레이너 리스트 조회", description = "트레이너 리스트 조회")
    @GetMapping("/manager")
    public ResponseEntity<?> managerList(LocalDate classTime) {
        return ResponseEntity.ok().body(masterService.getManager(classTime));
    }

    @Operation(summary = "회원 리스트 조회", description = "회원 리스트 조회")
    @GetMapping("/users")
    public List<RespMemberListDto> getMembersByNickname(@RequestParam(required = false, defaultValue = "") String nickName) {
        return masterService.searchMembers(nickName);
    }

    @Operation(summary = "지정된 날짜 매출 조회", description = "지정된 날짜 매출 조회")
    @GetMapping("/sales/reports")
    public ResponseEntity<?> getSalesWithStartAndEndDate(ReqDateDto reqDateDto) {
        return ResponseEntity.ok().body(masterService.getSalesWithStartAndEndDate(reqDateDto));
    }

}
