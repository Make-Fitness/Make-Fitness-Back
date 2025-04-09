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

    // 회원가입자 권한 변경
    @Transactional(rollbackFor = Exception.class)
    public void changeRoleName(int userId, String roleName) {
        masterRepository.updateRoleByUserId(userId, roleName);
    }

    // 월별 관련 트레이너 목록 조회
    public List<RespManagerDto> getManager(LocalDate classTime) {
        return masterRepository.getManager(classTime);
    }

    // 이름으로 검색 가능한 회원 조회
    public List<RespMemberListDto> searchMembers(String nickName) {
        return masterRepository.searchMembers(nickName);
    }

    // 지정한 날짜에 대한 매출 조회
    public List<RespSalesDto> getSalesWithStartAndEndDate(ReqDateDto reqDateDto) {
        return masterRepository.searchSalesWithDates(reqDateDto);
    }

    // 월별 매출 조회
    public List<RespSalesDto> getSales(LocalDate startDate) {
        return masterRepository.getSales(startDate);
    }

}
