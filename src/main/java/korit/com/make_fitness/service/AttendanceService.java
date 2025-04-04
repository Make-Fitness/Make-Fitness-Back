package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqAttendanceDto;
import korit.com.make_fitness.entity.DayAttendance;
import korit.com.make_fitness.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Transactional(rollbackFor = Exception.class)
    public int attendHealth(int userId, ReqAttendanceDto reqAttendanceDto) {

        DayAttendance dayAttendance = DayAttendance.builder()
                .userId(userId)
                .username(reqAttendanceDto.getUsername())
                .ph(reqAttendanceDto.getPh())
                .build();

        return attendanceRepository.attend(dayAttendance);
    }
}
