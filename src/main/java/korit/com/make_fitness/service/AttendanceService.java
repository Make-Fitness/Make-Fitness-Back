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

    /**
     * ✅ 기존 방식 - 전화번호 기반 출석 처리 (단일 API용)
     */
    @Transactional(rollbackFor = Exception.class)
    public int attendHealth(ReqAttendanceDto reqAttendanceDto) {
        Integer userId = attendanceRepository.findUserIdByPhone(reqAttendanceDto.getPh());

        if (userId == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        if (attendanceRepository.hasAlreadyAttendedToday(userId)) {
            throw new IllegalStateException("오늘 이미 출석한 회원입니다.");
        }

        DayAttendance dayAttendance = DayAttendance.builder()
                .userId(userId)
                .ph(reqAttendanceDto.getPh())
                .build();

        return attendanceRepository.attend(dayAttendance);
    }

    /**
     * ✅ 신규 방식 - 전화번호로 userId 반환 (검증용)
     */
    public Integer findUserIdByPhone(String ph) {
        return attendanceRepository.findUserIdByPhone(ph);
    }

    /**
     * ✅ 신규 방식 - 오늘 이미 출석했는지 확인
     */
    public boolean hasAlreadyAttendedToday(int userId) {
        return attendanceRepository.hasAlreadyAttendedToday(userId);
    }

    /**
     * ✅ 신규 방식 - userId와 ReqAttendanceDto로 출석 등록
     */
    @Transactional(rollbackFor = Exception.class)
    public int attendWithUserId(int userId, ReqAttendanceDto dto) {
        DayAttendance dayAttendance = DayAttendance.builder()
                .userId(userId)
                .ph(dto.getPh())
                .build();

        return attendanceRepository.attend(dayAttendance);
    }
}
