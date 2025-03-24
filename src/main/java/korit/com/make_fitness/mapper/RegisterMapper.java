package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Class;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
    int save(Class classes);
}
