package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.response.RespAttendanceDto;
import korit.com.make_fitness.entity.DayAttendance;
import korit.com.make_fitness.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceRepository {

    @Autowired
    private AttendanceMapper attendanceMapper;

    // 출석 등록
    public int attend(DayAttendance dayAttendance) {
        return attendanceMapper.insert(dayAttendance);
    }

    // 전화번호로 userId 조회
    public Integer findUserIdByPhone(String ph) {
        return attendanceMapper.selectUserIdByPh(ph);
    }

    // 오늘 출석했는지 확인
    public boolean hasAlreadyAttendedToday(int userId) {
        return attendanceMapper.countTodayAttendance(userId) > 0;
    }

    public List<RespAttendanceDto> findAttendanceByUserId(int userId) {
        return attendanceMapper.selectAttendanceByUserId(userId);
    }
}
