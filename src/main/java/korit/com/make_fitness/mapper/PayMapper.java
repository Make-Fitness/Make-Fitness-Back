package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.request.SalesDto;
import korit.com.make_fitness.entity.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface PayMapper {
    int insert(Pay pay);

    List<SalesDto> findSales(Date startDate);
}
