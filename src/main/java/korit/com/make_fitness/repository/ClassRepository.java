package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ClassRepository {

    @Autowired
    private ClassMapper classMapper;

    // 수업 등록
    public void insertClass(Class classEntity) {
        classMapper.insertClass(classEntity);
    }

    // 전체 수업 조회
    public List<Class> findAllUserAndSubject() {
        return classMapper.findAllUserAndSubject();
    }

    // 수업 필터링 조회
    public List<Class> findFiltered(String subject, String manager) {
        return classMapper.findFiltered(subject, manager);
    }

    // ClassId로 조회
    public Class findById(int classId) {
        return classMapper.findById(classId);
    }

    // 예약 인원 +1 증가
    public void increaseCustomerReserve(int classId) {
        classMapper.increaseCustomerReserve(classId);
    }

    // 수업 삭제
    public void deleteClassById(int classId) {
        classMapper.deleteClassById(classId);
    }

    // 예약 인원 -1 감소
    public void decreaseCustomerReserve(int classId) {
        classMapper.decreaseCustomerReserve(classId);
    }

    // 트레이너가 등록한 수업 + 예약자 목록까지 한 번에 조회
    public List<RespClassReservationRow> findClassWithReservations(int managerId) {
        return classMapper.findClassWithReservations(managerId);
    }

    // 트레이너가 등록 가능한 수업 subjectId 목록 조회
    public List<Integer> getAllowedSubjectIdsByTrainer(int userId) {
        return classMapper.getAllowedSubjectIdsByTrainer(userId);
    }

    // 중복된 수업 시간 존재 여부 확인
    public boolean existsByTrainerAndTime(int userId, LocalDateTime classTime) {
        return classMapper.existsByTrainerAndTime(userId, classTime);
    }

    // 특정 날짜에 등록된 시간만 조회
    public List<Integer> findRegisteredTimesByTrainerAndDate(int userId, String date) {
        return classMapper.findRegisteredTimesByTrainerAndDate(userId, date);
    }

    // 예약된 membershipId 리스트 조회
    public List<Integer> findMembershipIdsByClassId(int classId) {
        return classMapper.findMembershipIdsByClassId(classId);
    }

    // 예약된 membership 세션 일괄 복원
    public void restoreSessionCountsByMembershipIds(List<Integer> membershipIds) {
        classMapper.restoreSessionCountsByMembershipIds(membershipIds);
    }
}
