package rw.auca.salonbookingsystem.controller;

import rw.auca.salonbookingsystem.model.Booking;
import rw.auca.salonbookingsystem.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {


private final BookingService bookingService;

public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
}

@PostMapping("/save")
public ResponseEntity<String> save(@RequestBody Booking booking) {
    String message = bookingService.save(booking);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
}

@GetMapping("/all")
public ResponseEntity<List<Booking>> getAll() {
    return ResponseEntity.ok(bookingService.getAll());
}

@GetMapping("/{id}")
public ResponseEntity<?> getById(@PathVariable UUID id) {
    Optional<Booking> booking = bookingService.getById(id);

    if (booking.isPresent()) {
        return ResponseEntity.ok(booking.get());
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The booking with that id does not exist");
}

@PutMapping("/update/{id}")
public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Booking booking) {
    String message = bookingService.update(id, booking);

    if (message.equals("The booking with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    return ResponseEntity.ok(message);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> delete(@PathVariable UUID id) {
    String message = bookingService.delete(id);

    if (message.equals("The booking with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    return ResponseEntity.ok(message);
}


}
