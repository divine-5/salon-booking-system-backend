package rw.auca.salonbookingsystem.controller;

import rw.auca.salonbookingsystem.model.Stylist;
import rw.auca.salonbookingsystem.service.StylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/stylists")
public class StylistController {

    private final StylistService stylistService;

    public StylistController(StylistService stylistService) {
        this.stylistService = stylistService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Stylist stylist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stylistService.save(stylist));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stylist>> getAll() {
        return ResponseEntity.ok(stylistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        Optional<Stylist> stylist = stylistService.getById(id);

        if (stylist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The stylist with that id does not exist");
        }

        return ResponseEntity.ok(stylist.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
        @PathVariable UUID id,
        @RequestBody Stylist stylist) {

            String result = stylistService.update(id, stylist);

            if (result.equals("The stylist with that id does not exist")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
           }

            return ResponseEntity.ok(result);
        }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        String result = stylistService.delete(id);

        if (result.equals("The stylist with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-salon-service")
    public ResponseEntity<List<Stylist>> getBySalonServiceName(
            @RequestParam String name) {
        return ResponseEntity.ok(stylistService.getBySalonServiceName(name));
    }

    @PutMapping("/{stylistId}/salon-services/{salonServiceId}")
    public ResponseEntity<String> addSalonService(
            @PathVariable UUID stylistId,
            @PathVariable UUID salonServiceId) {

        String result = stylistService.addSalonService(stylistId, salonServiceId);

        if (result.equals("The stylist with that id does not exist")
                || result.equals("That salon service does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("The stylist already has that salon service")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{stylistId}/station")
    public ResponseEntity<String> changeStation(
            @PathVariable UUID stylistId,
            @RequestParam int stationNumber) {

        String result = stylistService.changeStation(stylistId, stationNumber);

        if (result.equals("The stylist with that id does not exist")
                || result.equals("The station with that stationNumber does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("That station is taken by another stylist")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        return ResponseEntity.ok(result);
    }
}

