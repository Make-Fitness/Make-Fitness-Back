package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Membership;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MembershipMapper {

    // 멤버십 구입한 가입자
    int insert(Membership membership);

    // 세션 카운트 차감
    int updateSessionCount(int membershipId);

    // userId로 조회
    Membership findByUserId(int userId);

    // 멤버십 고유키로 userId 조회
    int findUserIdByMembershipId(@Param("membershipId") int membershipId);

    // 세션 카운트 증가
    int restoreSessionCount(@Param("membershipId") int membershipId);

}
