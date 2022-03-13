package sample.service;

import sample.model.Destination;
import sample.model.Package;
import sample.repository.DestinationRepository;


import java.util.ArrayList;
import java.util.List;

public class DestinationService {
    DestinationRepository destinationRepository = new DestinationRepository();


    public void addDestination(Destination d){
        destinationRepository.insertDestination(d);
    }

    public List<Destination> retreiveDestinations() {
        List<Destination> listOfDestinations;
        listOfDestinations= destinationRepository.selectAllDestinations();
        return listOfDestinations;
    }
}
