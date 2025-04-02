package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.request.ReqDateDto;
import korit.com.make_fitness.dto.response.RespManagerDto;
import korit.com.make_fitness.dto.response.RespMemberListDto;
import korit.com.make_fitness.dto.response.RespSalesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MasterMapper {
    
    // 월별 트레이너 정보
    List<RespManagerDto> findManager(@Param("classTime") LocalDate classTime);

    // 매니저 삭제
    int deleteManager(@Param("userId") int userId, @Param("roleName") String roleName);

    // 권한 부여
    int updateRoleName(int userId);

    // 회원 리스트 조회
    List<RespMemberListDto> findByNickname(@Param("name") String nickName);

    // 날짜별 매출 조회
    List<RespSalesDto> findByStartAndEndDate(ReqDateDto reqDateDto);
}
