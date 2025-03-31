package korit.com.make_fitness.mapper;

import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassMapper {

    int insertClass (Class classEntity);

    List<Class> findAllUserAndSubject ();

    List<Class> findFiltered(
            @Param("subject") String subject,
            @Param("manager") String manager
    );

    Class findById(@Param("classId") int classId);

    void increaseCustomerReserve(int classId);

    void deleteClassById(int classId);

    void decreaseCustomerReserve(@Param("classId") int classId);

    // 트레이너가 등록한 수업 + 예약자 목록까지 한 번에 조회
    List<RespClassReservationRow> findClassWithReservations(@Param("managerId") int managerId);
}
