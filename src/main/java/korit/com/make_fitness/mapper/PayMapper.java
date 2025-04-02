package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.response.RespSalesDto;
import korit.com.make_fitness.entity.Pay;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PayMapper {
    int insert(Pay pay);

    List<RespSalesDto> findSales(LocalDate startDate);
}
