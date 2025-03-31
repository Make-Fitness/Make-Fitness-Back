package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.response.RespMemberManageDto;
import korit.com.make_fitness.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<RespMemberManageDto> searchMembers(String nickname) {
        return memberRepository.findByNickname(nickname);
    }
}
