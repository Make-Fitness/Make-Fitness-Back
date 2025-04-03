package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.dto.request.ReqUpdateMembershipDto;
import korit.com.make_fitness.entity.Customer;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.repository.MembershipRepository;
import korit.com.make_fitness.repository.UserRepository;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> 7a92de3d2b2a9a9fe8bdcd762b113a6ee33bc645
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

<<<<<<< HEAD
=======
    public MembershipService(MembershipRepository membershipRepository, UserRepository userRepository) {
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }
>>>>>>> 7a92de3d2b2a9a9fe8bdcd762b113a6ee33bc645

    @Transactional(rollbackFor = Exception.class)
    public Membership insertCustomer(ReqMembershipDto reqMembershipDto) {
        Membership membership = Membership.builder()
                .userId(reqMembershipDto.getUserId())
                .promotionId(reqMembershipDto.getPromotionId())
                .build();

        membershipRepository.save(membership);
<<<<<<< HEAD
=======
        System.out.println("✅ membership insert 성공");

        int count = userRepository.updateRoleToCustomer(userId);
        System.out.println("🧨 update count = " + count);

        String newRole = userRepository.findByUserId(userId).get().getRoleName();
        System.out.println("🧪 현재 role_name = " + newRole);
>>>>>>> 7a92de3d2b2a9a9fe8bdcd762b113a6ee33bc645
        return membership;
    }

    public Membership getMembershipByUserId(int userId) {
        return membershipRepository.findByUserId(userId);
    }

}
