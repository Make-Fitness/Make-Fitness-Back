package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import korit.com.make_fitness.security.principal.PrincipalUser;
import korit.com.make_fitness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/makefitness")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "token 생성", description = "토큰 생성 설명")
    @GetMapping("/user/me")
    public ResponseEntity<?> getLoginUser(@Parameter(hidden = true) @AuthenticationPrincipal PrincipalUser principalUser) {

        return ResponseEntity.ok().body(principalUser.getUser().getNickname());
    }
}
