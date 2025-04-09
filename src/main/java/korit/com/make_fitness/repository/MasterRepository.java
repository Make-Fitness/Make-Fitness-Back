package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.request.ReqDateDto;
import korit.com.make_fitness.dto.response.RespManagerDto;
import korit.com.make_fitness.dto.response.RespMemberListDto;
import korit.com.make_fitness.dto.response.RespSalesDto;
import korit.com.make_fitness.mapper.MasterMapper;
import korit.com.make_fitness.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MasterRepository {

    @Autowired
    private MasterMapper masterMapper;

    @Autowired
    private UserMapper userMapper;

    // userId로 조회하여 회원 권한 부여
    public void updateRoleByUserId(int userId, String roleName) {
        masterMapper.updateRoleName(userId, roleName);
    }

    // 월별 관련 트레이너 조회
    public List<RespManagerDto> getManager(LocalDate classTime) {
        return masterMapper.findManager(classTime);
    }

    // 이름으로 조회 가능한 회원가입자 조회
    public List<RespMemberListDto> searchMembers(String nickname) {
        return masterMapper.findByNickname(nickname);
    }

    // 지정 날짜에 대한 매출 조회
    public List<RespSalesDto> searchSalesWithDates(ReqDateDto reqDateDto) {
        return masterMapper.findByStartAndEndDate(reqDateDto);
    }

    // 월별 매출 조회
    public List<RespSalesDto> getSales(LocalDate startDate) {
        return masterMapper.findSales(startDate);
    }

    // 멤버십 가입 시 roleName = ROLE_CUSTOMER
    public void updateRoleName(int userId) {
        userMapper.updateUserRoleToCustomer(userId);
    }
}
