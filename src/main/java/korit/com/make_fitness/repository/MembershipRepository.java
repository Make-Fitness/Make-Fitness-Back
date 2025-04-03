package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Customer;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.mapper.MembershipMapper;
import korit.com.make_fitness.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class MembershipRepository {
    @Autowired
    private MembershipMapper membershipMapper;

    public Optional<Membership> save(Membership membership) {
        try {
            membershipMapper.insert(membership);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(membership);
    }

    public Membership findByUserId(int userId) {
        return membershipMapper.findByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateSessionCount(int membershipId) {
        membershipMapper.updateSessionCount(membershipId);
    }

    public int findPromotionSessionCount(int membershipId) {
        return membershipMapper.getPromotionSessionCount(membershipId);
    }

    public int findUserIdByMembershipId(int membershipId) {
        return membershipMapper.findUserIdByMembershipId(membershipId);
    }

    // 멤버십 세션 수 증가
    public void restoreSessionCount(int membershipId) {
        membershipMapper.restoreSessionCount(membershipId);
    }
}
