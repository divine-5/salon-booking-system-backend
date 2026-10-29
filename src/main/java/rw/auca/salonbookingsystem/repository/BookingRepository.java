package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}

