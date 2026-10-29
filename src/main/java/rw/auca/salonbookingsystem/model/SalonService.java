package rw.auca.salonbookingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class SalonService {


@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

private String name;

@JsonIgnore
@ManyToMany(mappedBy = "salonServices")
private List<Stylist> stylists = new ArrayList<>();

public SalonService() {
}

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public List<Stylist> getStylists() {
    return stylists;
}

public void setStylists(List<Stylist> stylists) {
    this.stylists = stylists;
}


}
