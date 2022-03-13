package sample.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "destination")
public class Destination {


        @Id
        private int id;

        @Column(nullable = false)
        private String destinationName;

        @OneToMany( cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "destination")
        private List <Package> packagesList;

    public Destination() {

    }

        public Destination(int id, String destination) {
            this.id = id;
            this.destinationName = destination;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public List<Package> getPackagesList() {
        return packagesList;
    }

    public void setPackagesList(List<Package> packagesList) {
        this.packagesList = packagesList;
    }
}
