package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.Booking;
import rw.auca.salonbookingsystem.model.BookingStatus;
import rw.auca.salonbookingsystem.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByStatusOrderByBookingDateAsc(BookingStatus status);

    List<Booking> findByBookingDateBetweenOrderByBookingDateAsc(LocalDate start, LocalDate end);

    boolean existsByStylistAndBookingDateAndStatusNot(Stylist stylist, LocalDate bookingDate, BookingStatus status);
}

