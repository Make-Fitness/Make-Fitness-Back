package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.dto.request.ReqPayDto;
import korit.com.make_fitness.repository.MembershipRepository;
import korit.com.make_fitness.repository.PayRepository;
import korit.com.make_fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PayService {
    @Autowired
    private PayRepository payRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    // 결제건 등록(+등급 변경 기능 추가)
    @Transactional(rollbackFor = Exception.class)
    public void registerPay(ReqMembershipDto reqMembershipDto, ReqPayDto reqPayDto) {
        membershipRepository.save(reqMembershipDto.toMembership());
        payRepository.save(reqPayDto.toPay());

        userRepository.updateRoleName(reqMembershipDto.getUserId());
    }


}
