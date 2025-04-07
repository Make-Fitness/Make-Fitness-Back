package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ClassMapper {

    int insertClass(Class classEntity);

    List<Class> findAllUserAndSubject();

    List<Class> findFiltered(@Param("subject") String subject, @Param("manager") String manager);

    List<Integer> getAllowedSubjectIdsByTrainer(@Param("userId") int userId);

    boolean existsByTrainerAndTime(@Param("userId") int userId, @Param("classTime") LocalDateTime classTime);

    Class findById(@Param("classId") int classId);

    void increaseCustomerReserve(@Param("classId") int classId);

    void deleteClassById(@Param("classId") int classId);

    void decreaseCustomerReserve(@Param("classId") int classId);

    List<RespClassReservationRow> findClassWithReservations(@Param("managerId") int managerId);

    List<Integer> findRegisteredTimesByTrainerAndDate(@Param("userId") int userId, @Param("date") String date);

    List<Integer> findMembershipIdsByClassId(@Param("classId") int classId);

    void restoreSessionCountsByMembershipIds(@Param("membershipIds") List<Integer> membershipIds);
}