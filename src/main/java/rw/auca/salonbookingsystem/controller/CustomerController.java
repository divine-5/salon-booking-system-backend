package rw.auca.salonbookingsystem.controller;

import rw.auca.salonbookingsystem.model.Customer;
import rw.auca.salonbookingsystem.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Customer customer) {
        String message = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Customer> customer = customerService.getById(id);

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The customer with that id does not exist");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Customer customer) {
        String message = customerService.update(id, customer);

        if (message.equals("The customer with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = customerService.delete(id);

        if (message.equals("The customer with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Customer>> getByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(customerService.getByLastName(lastName));
    }

    @GetMapping("/of-stylist/{stylistId}")
    public ResponseEntity<?> getCustomersOfStylist(@PathVariable UUID stylistId) {
        List<Customer> customers = customerService.getCustomersOfStylist(stylistId);

        if (customers == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The stylist with that id does not exist");
        }

        return ResponseEntity.ok(customers);
    }
}