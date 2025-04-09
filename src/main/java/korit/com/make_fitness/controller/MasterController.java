package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqDateDto;
import korit.com.make_fitness.dto.request.ReqRightDto;
import korit.com.make_fitness.dto.response.RespMemberListDto;
import korit.com.make_fitness.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/makefitness/admin")
public class MasterController {

    @Autowired
    private MasterService masterService;

    // master 권한으로 회원 권한 변경
    @Operation(summary = "회원 권한 변경", description = "회원 번호에 따라 master 권한으로 회원 권한 변경")
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<?> changeRoleName(@PathVariable int userId, @RequestBody ReqRightDto reqRightDto) {
        masterService.changeRoleName(userId, reqRightDto.getRoleName());
        return ResponseEntity.ok().build();
    }

    // 월별에 따른 트레이너 리스트 조회
    @Operation(summary = "트레이너 리스트 조회", description = "월별에 따른 트레이너 리스트 조회")
    @GetMapping("/manager")
    public ResponseEntity<?> managerList(LocalDate classTime) {
        return ResponseEntity.ok().body(masterService.getManager(classTime));
    }

    // 회원 리스트 조회(다건 조회 및 이름 단건)
    @Operation(summary = "회원 리스트 조회", description = "회원 리스트 조회")
    @GetMapping("/users")
    public List<RespMemberListDto> getMembersByNickname(@RequestParam(required = false, defaultValue = "") String nickName) {
        return masterService.searchMembers(nickName);
    }

    // 지정된 날짜 매출 조회
    @Operation(summary = "지정된 날짜 매출 조회", description = "지정된 날짜 매출 조회")
    @GetMapping("/sales/reports")
    public ResponseEntity<?> getSalesWithStartAndEndDate(ReqDateDto reqDateDto) {
        return ResponseEntity.ok().body(masterService.getSalesWithStartAndEndDate(reqDateDto));
    }

    // 매월 매출 조회
    @Operation(summary = "매출 조회", description = "매출 무조건 말까지 조회")
    @GetMapping("/sales/report")
    public ResponseEntity<?> findSales(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startDate) {
        return ResponseEntity.ok().body(masterService.getSales(startDate));
    }

}
