package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, UUID> {

    List<ServiceRecord> findByBookingCustomerPhoneOrderByServiceDateDesc(String phone);

    boolean existsByBookingId(UUID bookingId);
}