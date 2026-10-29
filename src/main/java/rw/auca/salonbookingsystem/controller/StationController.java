package rw.auca.salonbookingsystem.controller;

import rw.auca.salonbookingsystem.model.Station;
import rw.auca.salonbookingsystem.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/stations")
public class StationController {

private final StationService stationService;

public StationController(StationService stationService) {
    this.stationService = stationService;
}

@PostMapping("/save")
public ResponseEntity<String> save(@RequestBody Station station) {
    String message = stationService.save(station);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
}

@GetMapping("/all")
public ResponseEntity<List<Station>> getAll() {
    return ResponseEntity.ok(stationService.getAll());
}

@GetMapping("/{id}")
public ResponseEntity<?> getById(@PathVariable UUID id) {
    Optional<Station> station = stationService.getById(id);

    if (station.isPresent()) {
        return ResponseEntity.ok(station.get());
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The station with that id does not exist");
}

@PutMapping("/update/{id}")
public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Station station) {
    String message = stationService.update(id, station);

    if (message.equals("The station with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    return ResponseEntity.ok(message);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> delete(@PathVariable UUID id) {
    String message = stationService.delete(id);

    if (message.equals("The station with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    return ResponseEntity.ok(message);
}


}
