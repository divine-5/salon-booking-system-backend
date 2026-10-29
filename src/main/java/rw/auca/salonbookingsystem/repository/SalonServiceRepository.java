package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalonServiceRepository extends JpaRepository<SalonService, UUID> {
}

