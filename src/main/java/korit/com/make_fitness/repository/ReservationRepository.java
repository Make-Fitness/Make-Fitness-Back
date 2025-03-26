package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationMapper reservationMapper;

    public Reservation save(Reservation reservation) {
        reservationMapper.insertReservation(reservation);
        return reservation;
    }

    public List<Reservation> findAllByCustomerId(int customerId) {

        return reservationMapper.findAllByCustomerId(customerId);
    }
}
