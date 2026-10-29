package rw.auca.salonbookingsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Station {

@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

private int stationNumber;

private String name;

public Station() {
}

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public int getStationNumber() {
    return stationNumber;
}

public void setStationNumber(int stationNumber) {
    this.stationNumber = stationNumber;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

}
