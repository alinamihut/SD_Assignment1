package sample.service;

import sample.model.Package;
import sample.repository.PackageRepository;

import java.util.ArrayList;
import java.util.List;

public class PackageService {
    PackageRepository packageRepository = new PackageRepository();

    public void addPackage(Package p){
        packageRepository.insertPackage(p);
    }

    public List<Package> retreivePackages(){
        List<Package> listOfPackages;
        listOfPackages = packageRepository.selectAllPackages();
        return listOfPackages;
    }
}
