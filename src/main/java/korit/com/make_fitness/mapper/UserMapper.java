package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insert(User user);
}
