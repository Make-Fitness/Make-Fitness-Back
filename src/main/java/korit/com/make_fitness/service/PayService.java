package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.dto.request.ReqPayDto;
import korit.com.make_fitness.dto.request.SalesDto;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.entity.Pay;
import korit.com.make_fitness.repository.MembershipRepository;
import korit.com.make_fitness.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayService {
    @Autowired
    private PayRepository payRepository;

    @Autowired
    MembershipRepository membershipRepository;

    @Transactional(rollbackFor = Exception.class)
    public void registerPay(ReqMembershipDto reqMembershipDto, ReqPayDto reqPayDto) {
        membershipRepository.save(reqMembershipDto.toMembership());
        payRepository.save(reqPayDto.toPay());
    }

    public List<SalesDto> getSales(Date startDate) {
        return payRepository.getSales(startDate);
    }
}
