package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqClassDto;
import korit.com.make_fitness.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/makefitness")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Operation(summary = "매니저 권한 수업 등록", description = "매니저 권한 수업 등록 설명")
    @PostMapping("/classes")
    public ResponseEntity<?> addClass(@RequestBody ReqClassDto reqClassDto) {
        return ResponseEntity.ok().body(registerService.insertClass(reqClassDto));
    }
}
