package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqFinalMembershipDto;
import korit.com.make_fitness.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    
    // 매월 매출 조회
    @Operation(summary = "매출 조회", description = "매출 무조건 말까지 조회")
    @GetMapping("/admin/sales/report")
    public ResponseEntity<?> findSales(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startDate) {
        return ResponseEntity.ok().body(payService.getSales(startDate));
    }

}
