package rw.auca.salonbookingsystem.controller;

import rw.auca.salonbookingsystem.model.SalonService;
import rw.auca.salonbookingsystem.service.SalonServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/salon-services")
public class SalonServiceController {

private final SalonServiceService salonServiceService;

public SalonServiceController(SalonServiceService salonServiceService) {
    this.salonServiceService = salonServiceService;
}

@PostMapping("/save")
public ResponseEntity<String> save(@RequestBody SalonService salonService) {
    String message = salonServiceService.save(salonService);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
}

@GetMapping("/all")
public ResponseEntity<List<SalonService>> getAll() {
    return ResponseEntity.ok(salonServiceService.getAll());
}

@GetMapping("/{id}")
public ResponseEntity<?> getById(@PathVariable UUID id) {
    Optional<SalonService> salonService = salonServiceService.getById(id);

    if (salonService.isPresent()) {
        return ResponseEntity.ok(salonService.get());
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The salon service with that id does not exist");
}

@PutMapping("/update/{id}")
public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody SalonService salonService) {
    String message = salonServiceService.update(id, salonService);

    if (message.equals("The salon service with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    return ResponseEntity.ok(message);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> delete(@PathVariable UUID id) {
    String message = salonServiceService.delete(id);

    if (message.equals("The salon service with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    return ResponseEntity.ok(message);
}


}
