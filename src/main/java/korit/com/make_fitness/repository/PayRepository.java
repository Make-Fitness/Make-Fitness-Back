package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.request.SalesDto;
import korit.com.make_fitness.entity.Pay;
import korit.com.make_fitness.mapper.MembershipMapper;
import korit.com.make_fitness.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class PayRepository {
    @Autowired
    private PayMapper payMapper;

    public Optional<Pay> save(Pay pay) {
        try {
            payMapper.insert(pay);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(pay);
    }

    public List<SalesDto> getSales(Date startDate) {
        return payMapper.findSales(startDate);
    }

}
