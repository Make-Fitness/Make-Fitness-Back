package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassRepository {

    @Autowired
    private ClassMapper classMapper;

    public int insertClass(Class classEntity) {
        return classMapper.insertClass(classEntity);
    }
}
