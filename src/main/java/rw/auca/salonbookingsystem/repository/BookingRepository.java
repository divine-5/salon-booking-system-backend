package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.Booking;
import rw.auca.salonbookingsystem.model.BookingStatus;
import rw.auca.salonbookingsystem.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {


List<Booking> findByStatusOrderByBookingDateAsc(BookingStatus status);

List<Booking> findByBookingDateBetweenOrderByBookingDateAsc(LocalDate start, LocalDate end);

boolean existsByStylistAndBookingDateAndStatusNot(Stylist stylist, LocalDate bookingDate, BookingStatus status);

@Query("SELECT b.status, COUNT(b) FROM Booking b GROUP BY b.status")
List<Object[]> countBookingsByStatus();

@Modifying
@Query("UPDATE Booking b SET b.status = rw.auca.salonbookingsystem.model.BookingStatus.CANCELLED WHERE b.stylist.id = :stylistId AND b.bookingDate = :date AND b.status <> rw.auca.salonbookingsystem.model.BookingStatus.SERVED")
int cancelBookingsForStylistOnDate(@Param("stylistId") UUID stylistId, @Param("date") LocalDate date);

List<Booking> findByStylistIdAndBookingDateAndStatusNotIn(UUID stylistId, LocalDate bookingDate, List<BookingStatus> statuses);

long countByBookingDate(LocalDate bookingDate);

long countByStatus(BookingStatus status);

}
