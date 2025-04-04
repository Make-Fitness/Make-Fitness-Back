package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.mapper.MembershipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class MembershipRepository {
    
    @Autowired
    private MembershipMapper membershipMapper;

    // 멤버십 가입시 저장
    public Optional<Membership> save(Membership membership) {
        try {
            membershipMapper.insert(membership);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(membership);
    }

    // userId로 멤버십 가입자 조회
    public Membership findByUserId(int userId) {
        return membershipMapper.findByUserId(userId);
    }

    // 세션 카운트 차감
    @Transactional(rollbackFor = Exception.class)
    public void updateSessionCount(int membershipId) {
        membershipMapper.updateSessionCount(membershipId);
    }

    // 멤버십 고유키로 userId 조회
    public int findUserIdByMembershipId(int membershipId) {
        return membershipMapper.findUserIdByMembershipId(membershipId);
    }

    // 멤버십 세션 카운트 증가
    public void restoreSessionCount(int membershipId) {
        membershipMapper.restoreSessionCount(membershipId);
    }
}
