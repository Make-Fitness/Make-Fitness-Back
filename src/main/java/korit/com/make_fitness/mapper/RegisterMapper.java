package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterMapper {

    // 수업 등록
    int insert(List<Class> classes);

    // 수업 다건 조회
    List<Class> selectAll();

    // 수업 단건 조회
    Class selectByManagerId(int managerId);
}
