package sample.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table (name="package")
public class Package {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn (name = "destinationID", nullable = false)
    private Destination destination;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String extraDetails;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer nrOfBookings;

    @Column(nullable = false)
    private Status status;


    public Package() {

    }
    public Package(int id, String name, Integer price, LocalDate startDate, LocalDate endDate, String extraDetails, Integer capacity, Integer nrOfBookings, Status status, Destination destination) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraDetails = extraDetails;
        this.capacity = capacity;
        this.nrOfBookings = nrOfBookings;
        this.status = status;
        this.destination = destination;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getNrOfBookings() {
        return nrOfBookings;
    }

    public void setNrOfBookings(Integer nrOfBookings) {
        this.nrOfBookings = nrOfBookings;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
