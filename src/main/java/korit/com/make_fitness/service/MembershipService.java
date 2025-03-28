package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.dto.request.ReqUpdateMembershipDto;
import korit.com.make_fitness.entity.Customer;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Transactional(rollbackFor = Exception.class)
    public Membership insertCustomer(ReqMembershipDto reqMembershipDto) {
        Membership membership = Membership.builder()
                .userId(reqMembershipDto.getUserId())
                .promotionId(reqMembershipDto.getPromotionId())
                .build();

        membershipRepository.save(membership);
        return membership;
    }

    public Membership getMembershipByUserId(int userId) {
        return membershipRepository.findByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCountSession(int membershipId) {
        membershipRepository.updateSessionCount(membershipId);
        if(membershipRepository.findPromotionSessionCount(membershipId) < 0) {
            throw new IllegalArgumentException("promotion_session_count는 0 미만이 될 수 없습니다.");
        }
    }

}
