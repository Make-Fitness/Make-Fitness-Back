package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Customer;
import korit.com.make_fitness.mapper.MembershipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MembershipRepository {
    @Autowired
    private MembershipMapper membershipMapper;

    public Optional<Customer> save(Customer customer) {
        try {
            membershipMapper.insert(customer);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(customer);
    }

    public void update(Customer customer) {
        membershipMapper.update(customer);
    }
}
