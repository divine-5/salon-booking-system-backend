package rw.auca.salonbookingsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Booking {

@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

private LocalDate bookingDate;

@Enumerated(EnumType.STRING)
private BookingStatus status;

private String timeSlot;

@ManyToOne
@JoinColumn(name = "customer_id")
private Customer customer;

@ManyToOne
@JoinColumn(name = "stylist_id")
private Stylist stylist;

public Booking() {
}

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public LocalDate getBookingDate() {
    return bookingDate;
}

public void setBookingDate(LocalDate bookingDate) {
    this.bookingDate = bookingDate;
}

public BookingStatus getStatus() {
    return status;
}

public void setStatus(BookingStatus status) {
    this.status = status;
}

public String getTimeSlot() {
    return timeSlot;
}

public void setTimeSlot(String timeSlot) {
    this.timeSlot = timeSlot;
}

public Customer getCustomer() {
    return customer;
}

public void setCustomer(Customer customer) {
    this.customer = customer;
}

public Stylist getStylist() {
    return stylist;
}

public void setStylist(Stylist stylist) {
    this.stylist = stylist;
}


}
