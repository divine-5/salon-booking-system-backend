package rw.auca.salonbookingsystem.repository;

import rw.auca.salonbookingsystem.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StationRepository extends JpaRepository<Station, UUID> {

    @Query("SELECT st FROM Station st WHERE NOT EXISTS (SELECT s FROM Stylist s WHERE s.station = st) ORDER BY st.stationNumber")
    List<Station> findFreeStations();

    Optional<Station> findByStationNumber(int stationNumber);
}

