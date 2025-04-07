package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqDateDto;
import korit.com.make_fitness.dto.response.RespManagerDto;
import korit.com.make_fitness.dto.response.RespMemberListDto;
import korit.com.make_fitness.dto.response.RespSalesDto;
import korit.com.make_fitness.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MasterService {

    @Autowired
    private MasterRepository masterRepository;

    //
    @Transactional(rollbackFor = Exception.class)
    public void removeManager(String roleName, int userId) {
        masterRepository.deleteManager(roleName, userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeRoleName(int userId, String roleName) {
        masterRepository.updateRoleByUserId(userId, roleName);
    }

    public List<RespManagerDto> getManager(LocalDate classTime) {
        return masterRepository.getManager(classTime);
    }

    public List<RespMemberListDto> searchMembers(String nickName) {
        return masterRepository.searchMembers(nickName);
    }

    public List<RespSalesDto> getSalesWithStartAndEndDate(ReqDateDto reqDateDto) {
        return masterRepository.searchSalesWithDates(reqDateDto);
    }

    public List<RespSalesDto> getSales(LocalDate startDate) {
        return masterRepository.getSales(startDate);
    }

}
