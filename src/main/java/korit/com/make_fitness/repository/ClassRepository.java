package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    // ClassId 로 조회
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

    public List<RespClassReservationRow> findClassWithReservations(int managerId) {
        return classMapper.findClassWithReservations(managerId);
    }
}
