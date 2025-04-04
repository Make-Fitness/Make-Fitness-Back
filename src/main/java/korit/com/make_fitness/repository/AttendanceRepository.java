package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.request.ReqAttendanceDto;
import korit.com.make_fitness.entity.DayAttendance;
import korit.com.make_fitness.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceRepository {
    @Autowired
    private AttendanceMapper attendanceMapper;

    public int attend(DayAttendance dayAttendance) {
        return attendanceMapper.insert(dayAttendance);
    }
}
