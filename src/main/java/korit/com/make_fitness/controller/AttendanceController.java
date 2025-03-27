package korit.com.make_fitness.controller;

import korit.com.make_fitness.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/makefitness/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/users/{userId}")
    public ResponseEntity<?> attendanceCheck(@PathVariable("userId") int userId) {
        attendanceService.attendHealth(userId);
        return ResponseEntity.ok().build();
    }
}
