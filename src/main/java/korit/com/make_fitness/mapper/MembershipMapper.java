package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MembershipMapper {
    int insert(Customer customer);

    int update(Customer customer);
}
