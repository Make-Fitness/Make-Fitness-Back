package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.response.RespMemberManageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<RespMemberManageDto> findByNickname(@Param("name") String name);
}
