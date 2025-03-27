package korit.com.make_fitness.service;

import korit.com.make_fitness.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Transactional(rollbackFor = Exception.class)
    public void attendHealth(int userId) {
        attendanceRepository.attend(userId);
    }
}
