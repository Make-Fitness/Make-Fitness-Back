package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {

    int insertClass (Class classEntity);
    List<Class> findAllUserAndSubject ();
    List<Class> findBySubjectName (String subjectName);
    List<Class> findByNickName (String NickName);


}
