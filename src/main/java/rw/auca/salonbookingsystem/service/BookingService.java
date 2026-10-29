package rw.auca.salonbookingsystem.service;

import rw.auca.salonbookingsystem.model.Booking;
import rw.auca.salonbookingsystem.model.BookingStatus;
import rw.auca.salonbookingsystem.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public String save(Booking booking) {
        bookingRepository.save(booking);
        return "Booking saved successfully";
    }

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getById(UUID id) {
        return bookingRepository.findById(id);
    }

    public String update(UUID id, Booking booking) {
        if (!bookingRepository.existsById(id)) {
            return "The booking with that id does not exist";
        }

        booking.setId(id);
        bookingRepository.save(booking);
        return "Booking updated successfully";
    }

    public String delete(UUID id) {
        if (!bookingRepository.existsById(id)) {
            return "The booking with that id does not exist";
        }

        bookingRepository.deleteById(id);
        return "Booking deleted successfully";
    }

    public List<Booking> getByStatus(BookingStatus status) {
        return bookingRepository.findByStatusOrderByBookingDateAsc(status);
    }

    public List<Booking> getBetweenDates(LocalDate start, LocalDate end) {
        return bookingRepository.findByBookingDateBetweenOrderByBookingDateAsc(start, end);
    }
}