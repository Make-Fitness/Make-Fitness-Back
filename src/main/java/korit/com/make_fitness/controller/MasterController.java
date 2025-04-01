package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqManagerDto;
import korit.com.make_fitness.dto.request.ReqRightDto;
import korit.com.make_fitness.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @Operation(summary = "마스터 권한 부여", description = "마스터 권한 설명")
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<?> changeRoleName(@RequestBody ReqRightDto reqRightDto) {
        masterService.changeRoleName(reqRightDto.getUserId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "관리자 리스트 조회", description = "관리자 리스트 조회")
    @GetMapping("/manager")
    public ResponseEntity<?> managerList(LocalDate classTime) {
        return ResponseEntity.ok().body(masterService.getManager(classTime));
    }


}
