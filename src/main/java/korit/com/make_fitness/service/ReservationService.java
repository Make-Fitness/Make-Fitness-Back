package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional(rollbackFor = Exception.class)
    public Reservation createReservation(ReqReservationDto reqReservationDto) {

        Reservation reservation = Reservation.builder()
                .classId(reqReservationDto.getClassId())
                .membershipId(reqReservationDto.getMembershipId())
                .build();

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservationsByCustomerId(int customerId) {

        return reservationRepository.findAllByCustomerId(customerId);
    }
}
