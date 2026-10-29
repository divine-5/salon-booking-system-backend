package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT b.customer FROM Booking b WHERE b.stylist.id = :stylistId")
    List<Customer> findCustomersByStylistId(@Param("stylistId") UUID stylistId);
}