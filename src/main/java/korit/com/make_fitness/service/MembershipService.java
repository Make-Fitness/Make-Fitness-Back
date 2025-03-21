package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqCustomerDto;
import korit.com.make_fitness.dto.request.ReqUpdateCustomerDto;
import korit.com.make_fitness.entity.Customer;
import korit.com.make_fitness.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Transactional(rollbackFor = Exception.class)
    public Customer insertCustomer(ReqCustomerDto reqCustomerDto) {
        Customer customer = Customer.builder()
                .userId(reqCustomerDto.getUserId())
                .classStatus(reqCustomerDto.getClassStatus())
                .build();

        membershipRepository.save(customer);
        return customer;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCustomer(ReqUpdateCustomerDto reqUpdateCustomerDto) {



        Customer customer = Customer.builder()
                .customerId(reqUpdateCustomerDto.getCustomerId())
                .expireDate(reqUpdateCustomerDto.getJoinDate())
                .classSessionCount(reqUpdateCustomerDto.getClassSessionCount())
                .build();

        membershipRepository.update(customer);
    }
}
