package korit.com.make_fitness.controller;

import io.swagger.v3.oas.annotations.Operation;
import korit.com.make_fitness.dto.request.ReqReservationDto;
import korit.com.make_fitness.entity.Reservation;
import korit.com.make_fitness.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "수업조회(다건)", description = "수업조회 다건")
    @GetMapping("reservation/{customerId}")
    public ResponseEntity<?> getReservations(@PathVariable int customerId) {

        return ResponseEntity.ok().body(reservationService.getAllReservationsByCustomerId(customerId));
    }
}
