package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqJoinDto;
import korit.com.make_fitness.dto.request.ReqLoginDto;
import korit.com.make_fitness.dto.response.RespLoginDto;
import korit.com.make_fitness.dto.response.RespMembershipDto;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.service.MembershipService;
import korit.com.make_fitness.service.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private MembershipService membershipService;

    @Operation(summary = "회원가입", description = "회원가입 설명")
    @PostMapping("/signup")
    public ResponseEntity<?> join(@RequestBody ReqJoinDto reqJoinDto) {

        return ResponseEntity.ok().body(userService.join(reqJoinDto));
    }

    @Operation(summary = "로그인", description = "로그인 설명")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody ReqLoginDto reqLoginDto) throws NotFoundException {

        // 로그인 시도 → 실패 시 여기서 예외 발생 (BadCredentialsException 등)
        String token = userService.login(reqLoginDto);

        // 유저 정보 조회
        User user = userService.getUserByUsername(reqLoginDto);

        Membership membership = membershipService.getMembershipByUserId(user.getUserId());
        RespMembershipDto membershipDto = null;
        if(membership != null) {
            membershipDto = RespMembershipDto.builder()
                    .membershipId(membership.getMembershipId())
                    .promotionId(membership.getPromotionId())
                    .promotionSessionCount(membership.getPromotionSessionCount())
                    .promotionSessionTime(membership.getPromotionSessionTime())
                    .expiredDate(membership.getExpiredDate())
                    .build();
        }

        // 응답 객체 생성
        RespLoginDto respLoginDto = RespLoginDto.builder()
                .type("JWT")
                .name("AccessToken")
                .token(token)
                .nickname(user.getNickname())
                .ph(user.getPh())
                .roleName(user.getRoleName())
                .membership(membershipDto)
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
