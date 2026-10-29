package rw.auca.salonbookingsystem.service;

import rw.auca.salonbookingsystem.model.Station;
import rw.auca.salonbookingsystem.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public String save(Station station) {
        stationRepository.save(station);
        return "Station saved successfully";
    }

    public List<Station> getAll() {
        return stationRepository.findAll();
    }

    public Optional<Station> getById(UUID id) {
        return stationRepository.findById(id);
    }

    public String update(UUID id, Station station) {
        if (!stationRepository.existsById(id)) {
            return "The station with that id does not exist";
        }

        station.setId(id);
        stationRepository.save(station);
        return "Station updated successfully";
    }

    public String delete(UUID id) {
        if (!stationRepository.existsById(id)) {
            return "The station with that id does not exist";
        }

        stationRepository.deleteById(id);
        return "Station deleted successfully";
    }
}