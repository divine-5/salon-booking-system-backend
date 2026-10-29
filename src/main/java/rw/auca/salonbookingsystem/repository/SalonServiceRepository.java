package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SalonServiceRepository extends JpaRepository<SalonService, UUID> {

    @Query("SELECT ss FROM SalonService ss WHERE ss.stylists IS EMPTY")
    List<SalonService> findUnusedSalonServices();
}