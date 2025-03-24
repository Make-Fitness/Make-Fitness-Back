package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/makefitness")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Operation(summary = "수업예약", description = "수업예약 설명")
    @PostMapping("/reservation")
    public ResponseEntity<?> createReservation(@RequestBody ReqReservationDto reqReservationDto) {

        Reservation reservation = reservationService.createReservation(reqReservationDto);

        return ResponseEntity.ok().body(reservation);
    }
}
