package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional(rollbackFor = Exception.class)
    public Reservation createReservation(ReqReservationDto reqReservationDto) {

        Reservation reservation = Reservation.builder()
                .customerId(reqReservationDto.getCustomerId())
                .classId(reqReservationDto.getClassId())
                .bookingDateTime(reqReservationDto.getBookingDateTime())
                .bookingStatus("예약중")
                .build();

        return reservationRepository.save(reservation);
    }
}
