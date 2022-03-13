package sample.service;


import sample.model.Destination;
import sample.model.Package;
import sample.repository.DestinationRepository;
import sample.repository.PackageRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PackageService {
    PackageRepository packageRepository = new PackageRepository();
    DestinationRepository destinationRepository = new DestinationRepository();


    public boolean validateNumber(int n) {
        if (n<0) {
            return false;
        }
        return true;
    }

    public boolean validateTimeInterval (LocalDate startDate, LocalDate endDate){
        if (!startDate.isBefore(endDate)){
            return false;
        }
        return true;
    }
    public String addPackage(String name, Integer price, LocalDate startDate, LocalDate endDate, String extraDetails,
    Integer capacity, String destinationName){


        if (!validateNumber(price)){
            return "Please enter a valid price!";
        }
        if (!validateNumber(capacity)){
            return "Please enter a valid capacity!";
        }
        if (!validateTimeInterval(startDate,endDate)){
            return "Please enter a valid time interval!";
        }
        Destination destination = destinationRepository.searchDestinationFromName(destinationName);
        packageRepository.insertPackage(name,price, startDate, endDate, extraDetails, capacity, destination);
        return "Package inserted successfully!";
    }


    public List<Package> retrievePackages(){
        List<Package> listOfPackages;
        listOfPackages = packageRepository.selectAllPackages();
        return listOfPackages;
    }

    public String editPackage(String oldName, String name, Integer price, LocalDate startDate, LocalDate endDate, String extraDetails, Integer capacity, String d) {
        if (!validateNumber(price)){
            return "Please enter a valid price!";
        }
        if (!validateNumber(capacity)){
            return "Please enter a valid capacity!";
        }
        if (!validateTimeInterval(startDate,endDate)){
            return "Please enter a valid time interval!";
        }
        Destination destination = destinationRepository.searchDestinationFromName(d);
        Integer packageId = packageRepository.getPackageIDFromName(oldName);
        packageRepository.modifyPackage(packageId, name,price, startDate, endDate, extraDetails, capacity, destination);
        return "Package edited successfully!";
    }
        public void deletePackage(String packageName){
        packageRepository.deletePackage(packageName);
        }

    }

