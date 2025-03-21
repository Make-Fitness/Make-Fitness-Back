package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqCustomerDto;
import korit.com.make_fitness.dto.request.ReqUpdateCustomerDto;
import korit.com.make_fitness.service.MembershipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/makefitness")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @Operation(summary = "멤버십 선택", description = "멤버십 선택 설명")
    @PostMapping("/membership")
    public ResponseEntity<?> registerMembership(@RequestBody ReqCustomerDto reqCustomerDto) {
        return ResponseEntity.ok().body(membershipService.insertCustomer(reqCustomerDto));
    }

    @Operation(summary = "멤버십 가입", description = "멤버십 가입 설명")
    @PutMapping("/pilates")
    public ResponseEntity<?> updateMembership(@RequestBody ReqUpdateCustomerDto reqUpdateCustomerDto) {
        membershipService.updateCustomer(reqUpdateCustomerDto);
        return ResponseEntity.ok().build();
    }


}
