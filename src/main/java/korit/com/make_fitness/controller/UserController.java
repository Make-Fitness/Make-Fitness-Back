package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqJoinDto;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "회원가입", description = "회원가입 설명")
    @PostMapping("/signup")
    public ResponseEntity<User> join(@RequestBody ReqJoinDto reqJoinDto) {
        System.out.println("회원가입 요청 수신: " + reqJoinDto);

        return ResponseEntity.ok().body(userService.join(reqJoinDto));
    }
}
