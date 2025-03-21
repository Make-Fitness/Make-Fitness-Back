package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqJoinDto;
import korit.com.make_fitness.dto.request.ReqLoginDto;
import korit.com.make_fitness.dto.response.RespLoginDto;
import korit.com.make_fitness.dto.response.RespTokenDto;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.service.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "회원가입", description = "회원가입 설명")
    @PostMapping("/signup")
    public ResponseEntity<User> join(@RequestBody ReqJoinDto reqJoinDto) {
        System.out.println("회원가입 요청 수신: " + reqJoinDto);

        return ResponseEntity.ok().body(userService.join(reqJoinDto));
    }

    @Operation(summary = "로그인", description = "로그인 설명")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody ReqLoginDto reqLoginDto) throws NotFoundException {
        RespTokenDto respTokenDto = RespTokenDto.builder()
                .type("JWT")
                .name("AccessToken")
                .token(userService.login(reqLoginDto))
                .build();

        User user = userService.getUserByUsername(reqLoginDto);


        RespLoginDto respLoginDto = RespLoginDto.builder()
                .roleName(user.getRoleName())
                .nickname(user.getNickname())
                .ph(user.getPh())
                .type(respTokenDto.getType())
                .name(respTokenDto.getName())
                .token(respTokenDto.getToken())
                .build();
        
        return ResponseEntity.ok().body(respLoginDto);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경 설명")
    @PutMapping("/account/password")
    public ResponseEntity<?> updatePassword(int userId, String password) {
        userService.updatePasswordByUserId(userId, password);
        return ResponseEntity.ok().build();
    }

}
