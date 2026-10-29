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
        String message = stylistService.save(stylist);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stylist>> getAll() {
        return ResponseEntity.ok(stylistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Stylist> stylist = stylistService.getById(id);

        if (stylist.isPresent()) {
            return ResponseEntity.ok(stylist.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The stylist with that id does not exist");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Stylist stylist) {
        String message = stylistService.update(id, stylist);

        if (message.equals("The stylist with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = stylistService.delete(id);

        if (message.equals("The stylist with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }

    @GetMapping("/by-salon-service")
    public ResponseEntity<List<Stylist>> getBySalonServiceName(@RequestParam String name) {
        return ResponseEntity.ok(stylistService.getBySalonServiceName(name));
    }
}