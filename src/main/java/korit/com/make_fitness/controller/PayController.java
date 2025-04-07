package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqFinalMembershipDto;
import korit.com.make_fitness.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/makefitness")
public class PayController {
    @Autowired
    private PayService payService;

    // 결제건 저장
    @Operation(summary = "결제", description = "결제건 저장")
    @PostMapping("/pay")
    public ResponseEntity<?> save(@RequestBody ReqFinalMembershipDto reqFinalMembershipDto) {
        payService.registerPay(reqFinalMembershipDto.getReqMembershipDto(), reqFinalMembershipDto.getReqPayDto());
        return ResponseEntity.ok().build();
    }

}
