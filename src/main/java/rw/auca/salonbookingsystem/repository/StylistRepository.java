package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.Station;
import rw.auca.salonbookingsystem.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StylistRepository extends JpaRepository<Stylist, UUID> {

    @Query("SELECT DISTINCT s FROM Stylist s JOIN s.salonServices ss WHERE LOWER(ss.name) = LOWER(:name)")
    List<Stylist> findBySalonServiceName(@Param("name") String name);

    boolean existsByStation(Station station);
}

