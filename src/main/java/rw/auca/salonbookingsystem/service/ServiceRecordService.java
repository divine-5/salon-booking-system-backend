package rw.auca.salonbookingsystem.service;

import rw.auca.salonbookingsystem.model.Booking;
import rw.auca.salonbookingsystem.model.BookingStatus;
import rw.auca.salonbookingsystem.model.SalonService;
import rw.auca.salonbookingsystem.model.ServiceRecord;
import rw.auca.salonbookingsystem.repository.BookingRepository;
import rw.auca.salonbookingsystem.repository.SalonServiceRepository;
import rw.auca.salonbookingsystem.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;
    private final BookingRepository bookingRepository;
    private final SalonServiceRepository salonServiceRepository;

    public ServiceRecordService(
            ServiceRecordRepository serviceRecordRepository,
            BookingRepository bookingRepository,
            SalonServiceRepository salonServiceRepository) {
        this.serviceRecordRepository = serviceRecordRepository;
        this.bookingRepository = bookingRepository;
        this.salonServiceRepository = salonServiceRepository;
    }

    public String save(ServiceRecord serviceRecord) {

        if (serviceRecord.getBooking() == null ||
                serviceRecord.getBooking().getId() == null) {
            return "Booking information is required";
        }

        if (serviceRecord.getSalonService() == null ||
                serviceRecord.getSalonService().getId() == null) {
            return "Salon service information is required";
        }

        Optional<Booking> optionalBooking =
                bookingRepository.findById(serviceRecord.getBooking().getId());

        if (optionalBooking.isEmpty()) {
            return "The booking with that id does not exist";
        }

        Booking booking = optionalBooking.get();

        if (booking.getStatus() != BookingStatus.SERVED) {
            return "Service records can only be saved for served bookings";
        }

        Optional<SalonService> optionalSalonService =
                salonServiceRepository.findById(serviceRecord.getSalonService().getId());

        if (optionalSalonService.isEmpty()) {
            return "That salon service does not exist";
        }

        serviceRecord.setBooking(booking);
        serviceRecord.setSalonService(optionalSalonService.get());
        serviceRecord.setServiceDate(booking.getBookingDate());

        serviceRecordRepository.save(serviceRecord);

        return "Service record saved successfully";
    }

    public List<ServiceRecord> getByCustomerPhone(String phone) {
        return serviceRecordRepository.findByBookingCustomerPhoneOrderByServiceDateDesc(phone);
    }

    public String delete(UUID id) {

        if (!serviceRecordRepository.existsById(id)) {
            return "The service record with that id does not exist";
        }

        serviceRecordRepository.deleteById(id);

        return "Service record deleted successfully";
    }
}