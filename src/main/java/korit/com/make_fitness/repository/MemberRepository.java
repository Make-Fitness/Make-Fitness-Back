package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.response.RespMemberManageDto;
import korit.com.make_fitness.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @Autowired
    private MemberMapper memberMapper;

    public List<RespMemberManageDto> findByNickname(String nickname) {
        return memberMapper.findByNickname(nickname);
    }
}
