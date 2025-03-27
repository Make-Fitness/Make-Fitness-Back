package korit.com.make_fitness.mapper;

import korit.com.make_fitness.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

    int insertReservation(@Param("classId") int classId, @Param("membershipId") int membershipId);

    boolean existsByClassAndMembership(@Param("classId") int classId, @Param("membershipId") int membershipId);

    List<Reservation> findReservationsByMembershipId(@Param("membershipId") int membershipId);

    List<Integer> findClassIdListByMembershipId(@Param("membershipId") int membershipId);


}
