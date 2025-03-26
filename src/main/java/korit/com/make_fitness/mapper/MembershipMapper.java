package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Customer;
import korit.com.make_fitness.entity.Membership;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MembershipMapper {
    int insert(Membership membership);

    int update(Membership membership);

    Membership findByUserId(int userId);
}
