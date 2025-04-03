package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqMembershipDto;
import korit.com.make_fitness.entity.Membership;
import korit.com.make_fitness.repository.MembershipRepository;
import korit.com.make_fitness.repository.UserRepository;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> d4cf1dd4c9106fd07f61de22d09e53d101341cfc
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;

<<<<<<< HEAD
    public MembershipService(MembershipRepository membershipRepository, UserRepository userRepository) {
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }

    @Transactional
=======

    @Transactional(rollbackFor = Exception.class)
>>>>>>> d4cf1dd4c9106fd07f61de22d09e53d101341cfc
    public Membership insertCustomer(ReqMembershipDto reqMembershipDto) {
        int userId = reqMembershipDto.getUserId();

        Membership membership = Membership.builder()
                .userId(userId)
                .promotionId(reqMembershipDto.getPromotionId())
                .build();

        membershipRepository.save(membership);
<<<<<<< HEAD
        System.out.println("✅ membership insert 성공");

        int count = userRepository.updateRoleToCustomer(userId);
        System.out.println("🧨 update count = " + count);

        String newRole = userRepository.findByUserId(userId).get().getRoleName();
        System.out.println("🧪 현재 role_name = " + newRole);

=======
>>>>>>> d4cf1dd4c9106fd07f61de22d09e53d101341cfc
        return membership;
    }

    public Membership getMembershipByUserId(int userId) {
        return membershipRepository.findByUserId(userId);
    }
}
