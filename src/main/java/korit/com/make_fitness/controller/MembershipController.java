package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.dto.request.ReqUpdateMembershipDto;
import korit.com.make_fitness.service.MembershipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/makefitness")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @Operation(summary = "멤버십 선택", description = "멤버십 선택 설명")
    @PostMapping("/membership")
    public ResponseEntity<?> registerMembership(@RequestBody ReqMembershipDto reqMembershipDto) {
        return ResponseEntity.ok().body(membershipService.insertCustomer(reqMembershipDto));
    }

    @Operation(summary = "멤버십 세션 카운트 차감", description = "멤버십 세션 카운트 차감 설명")
    @PutMapping("/membership/{membershipId}")
    public ResponseEntity<?> updateMembership(@PathVariable("membershipId") int membershipId) {
//        membershipService.getSessionCount(membershipId);
        membershipService.updateCountSession(membershipId);
        return ResponseEntity.ok().build();
    }
}
