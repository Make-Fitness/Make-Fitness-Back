package korit.com.make_fitness.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper {
    int insert(int userId);
}
