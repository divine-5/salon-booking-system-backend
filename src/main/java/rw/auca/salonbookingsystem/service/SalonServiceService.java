package rw.auca.salonbookingsystem.service;

import rw.auca.salonbookingsystem.model.SalonService;
import rw.auca.salonbookingsystem.repository.SalonServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalonServiceService {

    private final SalonServiceRepository salonServiceRepository;

    public SalonServiceService(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }

    public String save(SalonService salonService) {
        salonServiceRepository.save(salonService);
        return "Salon service saved successfully";
    }

    public List<SalonService> getAll() {
        return salonServiceRepository.findAll();
    }

    public Optional<SalonService> getById(UUID id) {
        return salonServiceRepository.findById(id);
    }

    public String update(UUID id, SalonService salonService) {
        if (!salonServiceRepository.existsById(id)) {
            return "The salon service with that id does not exist";
        }

        salonService.setId(id);
        salonServiceRepository.save(salonService);
        return "Salon service updated successfully";
    }

    public String delete(UUID id) {
        if (!salonServiceRepository.existsById(id)) {
            return "The salon service with that id does not exist";
        }

        salonServiceRepository.deleteById(id);
        return "Salon service deleted successfully";
    }

    public List<SalonService> getUnusedSalonServices() {
        return salonServiceRepository.findUnusedSalonServices();
    }
}