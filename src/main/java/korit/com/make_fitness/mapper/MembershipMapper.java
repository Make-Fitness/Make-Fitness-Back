package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Customer;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.entity.Pay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MembershipMapper {
    int insert(Membership membership);
    int insert(Pay pay);
    int updateSessionCount(int membershipId);

    int getPromotionSessionCount(int membershipId);

    Membership findByUserId(int userId);
}
