package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.request.ReqManagerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterMapper {
    List<ReqManagerDto> findManager();

    int deleteManager(@Param("userId") int userId, @Param("roleName") String roleName);

    int updateRoleName(int userId);
}
