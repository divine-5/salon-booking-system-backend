package rw.auca.salonbookingsystem.controller;

import rw.auca.salonbookingsystem.model.ServiceRecord;
import rw.auca.salonbookingsystem.service.ServiceRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-records")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    public ServiceRecordController(ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ServiceRecord serviceRecord) {

        String result = serviceRecordService.save(serviceRecord);

        if (result.equals("The booking with that id does not exist") ||
                result.equals("That salon service does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("Service records can only be saved for served bookings")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        if (!result.equals("Service record saved successfully")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/by-customer-phone")
    public ResponseEntity<List<ServiceRecord>> getByCustomerPhone(
            @RequestParam String phone) {
        return ResponseEntity.ok(serviceRecordService.getByCustomerPhone(phone));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        String result = serviceRecordService.delete(id);

        if (result.equals("The service record with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }
}