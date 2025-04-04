package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.DayAttendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AttendanceMapper {

    /**
     * 출석 등록
     */
    int insert(DayAttendance dayAttendance);

    /**
     * 전화번호로 사용자 ID 조회
     */
    Integer selectUserIdByPh(@Param("ph") String ph);

    /**
     * 오늘 출석했는지 확인
     */
    int countTodayAttendance(@Param("userId") int userId);
}
