package rw.auca.salonbookingsystem.controller;

import rw.auca.salonbookingsystem.model.Booking;
import rw.auca.salonbookingsystem.model.BookingStatus;
import rw.auca.salonbookingsystem.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Booking booking) {

        String result = bookingService.save(booking);

        if (result.equals("Booking date cannot be in the past")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        if (result.equals("Stylist is already booked on that date")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAll() {
        return ResponseEntity.ok(bookingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        Optional<Booking> booking = bookingService.getById(id);

        if (booking.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The booking with that id does not exist");
        }

        return ResponseEntity.ok(booking.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Booking booking) {

        String result = bookingService.update(id, booking);

        if (result.equals("The booking with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        String result = bookingService.delete(id);

        if (result.equals("The booking with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("Cannot delete a booking that has service records")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<Booking>> getByStatus(@RequestParam BookingStatus status) {
        return ResponseEntity.ok(bookingService.getByStatus(status));
    }

    @GetMapping("/between")
    public ResponseEntity<List<Booking>> getBetween(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return ResponseEntity.ok(bookingService.getBetween(start, end));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable UUID id,
            @RequestParam BookingStatus status) {

        String result = bookingService.updateStatus(id, status);

        if (result.equals("The booking with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.startsWith("Cannot change status")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/stats/by-status")
    public ResponseEntity<List<Object[]>> getBookingStatsByStatus() {
        return ResponseEntity.ok(bookingService.getBookingStatsByStatus());
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelBookingsForStylistOnDate(
        @RequestParam UUID stylistId,
        @RequestParam LocalDate date) {
            String result = bookingService.cancelBookingsForStylistOnDate(stylistId, date);
            return ResponseEntity.ok(result);
        }
}

