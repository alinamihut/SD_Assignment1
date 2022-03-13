package sample.repository;

import sample.model.Package;
import sample.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PackageRepository {
    public static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    EntityManager entityManager=entityManagerFactory.createEntityManager();

    public void insertPackage(Package p){
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void modifyPackage(){

    }

    public void deletePackage(){

    }

    public List<Package> selectAllPackages() {
        Query query = entityManager.createQuery("SELECT p FROM Package p");
        List <Package> resultList = query.getResultList();
        return resultList;
    }
}
