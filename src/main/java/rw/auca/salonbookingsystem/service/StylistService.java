package rw.auca.salonbookingsystem.service;

import rw.auca.salonbookingsystem.model.Stylist;
import rw.auca.salonbookingsystem.repository.StylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StylistService {

    private final StylistRepository stylistRepository;

    public StylistService(StylistRepository stylistRepository) {
        this.stylistRepository = stylistRepository;
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
}