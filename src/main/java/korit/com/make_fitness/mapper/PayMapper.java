package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Pay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper {
    int insert(Pay pay);
}
