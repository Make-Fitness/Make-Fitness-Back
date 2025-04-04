package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/makefitness")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;
    
    // 멤버십 선택 및 결제
    @Operation(summary = "멤버십 선택", description = "멤버십 선택 및 결제")
    @PostMapping("/membership")
    public ResponseEntity<?> registerMembership(@RequestBody ReqMembershipDto reqMembershipDto) {
        return ResponseEntity.ok().body(membershipService.insertCustomer(reqMembershipDto));
    }

}
