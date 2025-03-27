package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassRepository {

    @Autowired
    private ClassMapper classMapper;

    // ✅ 수업 등록
    public void insertClass(Class classEntity) {
        classMapper.insertClass(classEntity);
    }

    // ✅ 전체 수업 조회
    public List<Class> findAllUserAndSubject() {
        return classMapper.findAllUserAndSubject();
    }

    // ✅ 수업명으로 조회
    public List<Class> findBySubjectName(String subjectName) {
        return classMapper.findBySubjectName(subjectName);
    }

    // ✅ 강사 닉네임으로 조회
    public List<Class> findByManagerNickname(String nickname) {
        return classMapper.findByNickName(nickname);
    }

    // ✅ 예약 인원 +1 증가
    public void increaseCustomerReserve(int classId) {
        classMapper.increaseCustomerReserve(classId);
    }
}
