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

    // 수업 등록
    public int insertClass(Class classEntity) {
        return classMapper.insertClass(classEntity);
    }

    // 수업 전체 조회
    public List<Class> findAllUserAndSubject() {
        return classMapper.findAllUserAndSubject();
    }

    // 수업 이름으로 조회
    public List<Class> findBySubjectName(String subjectName) {
        return classMapper.findBySubjectName(subjectName);
    }

    // 강사 이름으로 조회
    public List<Class> findByManagerNickname(String nickname) {
        return classMapper.findByNickName(nickname);
    }

}
