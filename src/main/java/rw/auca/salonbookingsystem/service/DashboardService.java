package rw.auca.salonbookingsystem.service;

import rw.auca.salonbookingsystem.model.BookingStatus;
import rw.auca.salonbookingsystem.repository.BookingRepository;
import rw.auca.salonbookingsystem.repository.CustomerRepository;
import rw.auca.salonbookingsystem.repository.ServiceRecordRepository;
import rw.auca.salonbookingsystem.repository.StationRepository;
import rw.auca.salonbookingsystem.repository.StylistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final StylistRepository stylistRepository;
    private final StationRepository stationRepository;
    private final BookingRepository bookingRepository;
    private final ServiceRecordRepository serviceRecordRepository;

    public DashboardService(
            CustomerRepository customerRepository,
            StylistRepository stylistRepository,
            StationRepository stationRepository,
            BookingRepository bookingRepository,
            ServiceRecordRepository serviceRecordRepository) {
        this.customerRepository = customerRepository;
        this.stylistRepository = stylistRepository;
        this.stationRepository = stationRepository;
        this.bookingRepository = bookingRepository;
        this.serviceRecordRepository = serviceRecordRepository;
    }

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("customers", customerRepository.count());
        summary.put("stylists", stylistRepository.count());
        summary.put("freeStations", (long) stationRepository.findFreeStations().size());
        summary.put("bookingsToday", bookingRepository.countByBookingDate(LocalDate.now()));
        summary.put("requested", bookingRepository.countByStatus(BookingStatus.REQUESTED));
        summary.put("serviceRecords", serviceRecordRepository.count());

        return summary;
    }
}