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

        if (booking.getBookingDate().isBefore(LocalDate.now())) {
            return "Booking date cannot be in the past";
        }

        if (bookingRepository.existsByStylistAndBookingDateAndStatusNot(
                booking.getStylist(), booking.getBookingDate(), BookingStatus.CANCELLED)) {
            return "Stylist is already booked on that date";
        }

        booking.setStatus(BookingStatus.REQUESTED);
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

        Optional<Booking> existingBooking = bookingRepository.findById(id);

        if (existingBooking.isEmpty()) {
            return "The booking with that id does not exist";
        }

        Booking currentBooking = existingBooking.get();

        currentBooking.setBookingDate(booking.getBookingDate());
        currentBooking.setTimeSlot(booking.getTimeSlot());
        currentBooking.setCustomer(booking.getCustomer());
        currentBooking.setStylist(booking.getStylist());

        bookingRepository.save(currentBooking);

        return "Booking updated successfully";
    }

    public String delete(UUID id) {

        if (!bookingRepository.existsById(id)) {
            return "The booking with that id does not exist";
        }

        bookingRepository.deleteById(id);

        return "Booking deleted successfully";
    }

    public String updateStatus(UUID id, BookingStatus newStatus) {

        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isEmpty()) {
            return "The booking with that id does not exist";
        }

        Booking booking = optionalBooking.get();
        BookingStatus currentStatus = booking.getStatus();

        boolean validTransition =
                (currentStatus == BookingStatus.REQUESTED &&
                        (newStatus == BookingStatus.ACCEPTED || newStatus == BookingStatus.CANCELLED))
                ||
                (currentStatus == BookingStatus.ACCEPTED &&
                        (newStatus == BookingStatus.SERVED || newStatus == BookingStatus.CANCELLED));

        if (!validTransition) {
            return "Cannot change status from " + currentStatus + " to " + newStatus;
        }

        booking.setStatus(newStatus);
        bookingRepository.save(booking);

        return "Booking status updated successfully";
    }

    public List<Booking> getByStatus(BookingStatus status) {
    return bookingRepository.findByStatusOrderByBookingDateAsc(status);
    }

    public List<Booking> getBetween(LocalDate start, LocalDate end) {
    return bookingRepository.findByBookingDateBetweenOrderByBookingDateAsc(start, end);
    }
}

