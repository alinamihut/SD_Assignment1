package sample.service;

import sample.model.Destination;
import sample.model.Package;
import sample.repository.DestinationRepository;


import java.util.ArrayList;
import java.util.List;

public class DestinationService {
    DestinationRepository destinationRepository = new DestinationRepository();


    public String addDestination(String destinationName){
        if (destinationRepository.searchDestinationFromName(destinationName)!= null){
            return "Destination already exists in the DB!";
        }
        else {
            destinationRepository.insertDestination(destinationName);

            return "Destination inserted successfully!";
        }
    }

    public void deleteDestination (String destinationName){
        destinationRepository.deleteDestination(destinationName);
    }

    public Destination retrieveDestination(String destinationName){
        Destination d = destinationRepository.searchDestinationFromName(destinationName);
        if (d != null) return d;
        else return null;
    }
    public List<Destination> retrieveAllDestinations() {
        List<Destination> listOfDestinations;
        listOfDestinations= destinationRepository.selectAllDestinations();
        return listOfDestinations;
    }
}
