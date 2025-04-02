package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.response.RespAvailablePromotionDto;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.entity.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface MembershipMapper {
    int insert(Membership membership);

    int insert(Pay pay);

    int updateSessionCount(int membershipId);

    int getPromotionSessionCount(int membershipId);

    Membership findByUserId(int userId);

    int findUserIdByMembershipId(@Param("membershipId") int membershipId);

    int restoreSessionCount(@Param("membershipId") int membershipId);

    int updateRoleName(@Param("userId") int userId);
}
