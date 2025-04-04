package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.request.ReqAttendanceDto;
import korit.com.make_fitness.entity.DayAttendance;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper {
    int insert(DayAttendance dayAttendance);
}
