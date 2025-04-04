package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;
    
    // 멤버십 가입
    @Transactional(rollbackFor = Exception.class)
    public Membership insertCustomer(ReqMembershipDto reqMembershipDto) {
        Membership membership = Membership.builder()
                .userId(reqMembershipDto.getUserId())
                .promotionId(reqMembershipDto.getPromotionId())
                .build();

        membershipRepository.save(membership);

        return membership;
    }

    // userId로 멤버십 조회
    public Membership getMembershipByUserId(int userId) {
        return membershipRepository.findByUserId(userId);
    }

}
