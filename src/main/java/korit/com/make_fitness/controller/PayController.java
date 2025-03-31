package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqFinalMembershipDto;
import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.dto.request.ReqPayDto;
import korit.com.make_fitness.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/makefitness")
public class PayController {
    @Autowired
    private PayService payService;

    @Operation(summary = "결제", description = "결제 설명")
    @PostMapping("/pay")
    public ResponseEntity<?> save(@RequestBody ReqFinalMembershipDto reqFinalMembershipDto) {
        payService.registerPay(reqFinalMembershipDto.getReqMembershipDto(), reqFinalMembershipDto.getReqPayDto());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "매출 조회", description = "매출 다건 조회 설명")
    @GetMapping("/admin/sales/report")
    public ResponseEntity<?> findSales(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd" ) Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return ResponseEntity.ok().body(payService.getSales(startDate));
    }

}
