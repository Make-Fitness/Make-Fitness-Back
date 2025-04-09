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

    // 회원 롤네임 수정
    int updateRoleName(@Param("userId") int userId, @Param("roleName") String roleName);

    // 회원 리스트 조회
    List<RespMemberListDto> findByNickname(@Param("name") String nickName);

    // 날짜별 매출 조회
    List<RespSalesDto> findByStartAndEndDate(ReqDateDto reqDateDto);

    // 매월 매출 조회
    List<RespSalesDto> findSales(LocalDate startDate);
}
