package rw.auca.salonbookingsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Stylist {

@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

private String firstName;

private String lastName;

private String phone;

@ManyToOne
@JoinColumn(name = "station_id")
private Station station;

@ManyToMany
@JoinTable(
    name = "stylist_salon_service",
    joinColumns = @JoinColumn(name = "stylist_id"),
    inverseJoinColumns = @JoinColumn(name = "salon_service_id")
)
private List<SalonService> salonServices = new ArrayList<>();

public Stylist() {
}

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public Station getStation() {
    return station;
}

public void setStation(Station station) {
    this.station = station;
}

public List<SalonService> getSalonServices() {
    return salonServices;
}

public void setSalonServices(List<SalonService> salonServices) {
    this.salonServices = salonServices;
}

}
