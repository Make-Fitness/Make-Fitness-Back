package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.request.ReqDateDto;
import korit.com.make_fitness.dto.response.RespManagerDto;
import korit.com.make_fitness.dto.response.RespMemberListDto;
import korit.com.make_fitness.dto.response.RespSalesDto;
import korit.com.make_fitness.mapper.MasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MasterRepository {

    @Autowired
    private MasterMapper masterMapper;

    public void deleteManager(String roleName, int userId) {
        masterMapper.deleteManager(userId, roleName);
    }

    public void updateRoleByUserId(int userId, String roleName) {
        masterMapper.updateRoleName(userId, roleName);
    }

    public List<RespManagerDto> getManager(LocalDate classTime) {
        return masterMapper.findManager(classTime);
    }

    public List<RespMemberListDto> searchMembers(String nickname) {
        return masterMapper.findByNickname(nickname);
    }

    public List<RespSalesDto> searchSalesWithDates(ReqDateDto reqDateDto) {
        return masterMapper.findByStartAndEndDate(reqDateDto);
    }
}
