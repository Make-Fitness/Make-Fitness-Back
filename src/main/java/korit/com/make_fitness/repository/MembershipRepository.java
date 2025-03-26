package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Customer;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.mapper.MembershipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

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

    public void update(Membership membership) {
        membershipMapper.update(membership);
    }
}
