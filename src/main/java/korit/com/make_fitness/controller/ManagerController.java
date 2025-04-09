package korit.com.make_fitness.controller;

import korit.com.make_fitness.dto.response.RespMemberManageDto;
import korit.com.make_fitness.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/makefitness/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final MemberService memberService;

    @GetMapping("/membermanagement")
    public List<RespMemberManageDto> getMembersByNickname(@RequestParam(required = false) String nickName) {
        return memberService.searchMembers(nickName);
    }
}
