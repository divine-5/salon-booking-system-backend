package rw.auca.salonbookingsystem.service;

import rw.auca.salonbookingsystem.model.SalonService;
import rw.auca.salonbookingsystem.model.Station;
import rw.auca.salonbookingsystem.model.Stylist;
import rw.auca.salonbookingsystem.repository.SalonServiceRepository;
import rw.auca.salonbookingsystem.repository.StationRepository;
import rw.auca.salonbookingsystem.repository.StylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StylistService {

    private final StylistRepository stylistRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final StationRepository stationRepository;

    public StylistService(
            StylistRepository stylistRepository,
            SalonServiceRepository salonServiceRepository,
            StationRepository stationRepository) {
        this.stylistRepository = stylistRepository;
        this.salonServiceRepository = salonServiceRepository;
        this.stationRepository = stationRepository;
    }

    public String save(Stylist stylist) {
        stylistRepository.save(stylist);
        return "Stylist saved successfully";
    }

    public List<Stylist> getAll() {
        return stylistRepository.findAll();
    }

    public Optional<Stylist> getById(UUID id) {
        return stylistRepository.findById(id);
    }

    public String update(UUID id, Stylist stylist) {

        if (!stylistRepository.existsById(id)) {
            return "The stylist with that id does not exist";
        }

        stylist.setId(id);
        stylistRepository.save(stylist);

        return "Stylist updated successfully";
    }

    public String delete(UUID id) {

        if (!stylistRepository.existsById(id)) {
            return "The stylist with that id does not exist";
        }

        stylistRepository.deleteById(id);

        return "Stylist deleted successfully";
    }

    public List<Stylist> getBySalonServiceName(String name) {
        return stylistRepository.findBySalonServiceName(name);
    }

    public String addSalonService(UUID stylistId, UUID salonServiceId) {

        Optional<Stylist> optionalStylist = stylistRepository.findById(stylistId);

        if (optionalStylist.isEmpty()) {
            return "The stylist with that id does not exist";
        }

        Optional<SalonService> optionalSalonService =
                salonServiceRepository.findById(salonServiceId);

        if (optionalSalonService.isEmpty()) {
            return "That salon service does not exist";
        }

        Stylist stylist = optionalStylist.get();
        SalonService salonService = optionalSalonService.get();

        if (stylist.getSalonServices().contains(salonService)) {
            return "The stylist already has that salon service";
        }

        stylist.getSalonServices().add(salonService);
        stylistRepository.save(stylist);

        return "Salon service added to the stylist";
    }

    public String changeStation(UUID stylistId, int stationNumber) {

        Optional<Stylist> optionalStylist = stylistRepository.findById(stylistId);

        if (optionalStylist.isEmpty()) {
            return "The stylist with that id does not exist";
        }

        Optional<Station> optionalStation =
                stationRepository.findByStationNumber(stationNumber);

        if (optionalStation.isEmpty()) {
            return "The station with that stationNumber does not exist";
        }

        Stylist stylist = optionalStylist.get();
        Station station = optionalStation.get();

        if (stylist.getStation() != null &&
                stylist.getStation().getId().equals(station.getId())) {
            return "Station changed successfully";
        }

        if (stylistRepository.existsByStation(station)) {
            return "That station is taken by another stylist";
        }

        stylist.setStation(station);
        stylistRepository.save(stylist);

        return "Station changed successfully";
    }
}

