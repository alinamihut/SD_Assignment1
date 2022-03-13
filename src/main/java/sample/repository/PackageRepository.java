package sample.repository;

import sample.model.Destination;
import sample.model.Package;
import sample.model.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class PackageRepository {
    public static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    EntityManager entityManager=entityManagerFactory.createEntityManager();
    DestinationRepository destinationRepository = new DestinationRepository();
    public void insertPackage(String name, Integer price, LocalDate startDate, LocalDate endDate, String extraDetails,
                              Integer capacity, Destination destination){
        EntityManager entityManager=entityManagerFactory.createEntityManager();

        int id = getNextAvailableIDForPackage();
        Destination d = destinationRepository.searchDestinationFromName(destination.getDestinationName());
        Package p = new Package(id, name, price, startDate, endDate, extraDetails, capacity, 0, Status.NOT_BOOKED, destination);
        d.getPackagesList().add(p);
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public void deletePackage(String name){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE from Package WHERE name= :name").setParameter("name", name).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public List<Package> selectAllPackages() {
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Package p");
        List <Package> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public int getNextAvailableIDForPackage (){
        return selectAllPackages().size() + 1;
    }

    public Integer getPackageIDFromName(String packageName){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.joinTransaction();
        try {

            Package p = em.createQuery(
                    "SELECT p from Package p WHERE p.name = :packageName", Package.class).
                    setParameter("packageName",packageName).getSingleResult();
            em.getTransaction().commit();
            em.close();
            return p.getId();
        }
        catch(NoResultException e){
            return null;
        }
    }


    public Package getPackageFromID(Integer id){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.joinTransaction();
        try {

            Package p = em.createQuery(
                    "SELECT p from Package p WHERE p.id = :id", Package.class).
                    setParameter("id",id).getSingleResult();
            em.getTransaction().commit();
            em.close();
            return p;
        }
        catch(NoResultException e){
            return null;
        }
    }
    public void modifyPackage(Integer id, String name, Integer price, LocalDate startDate, LocalDate endDate, String extraDetails, Integer capacity, Destination destination) {
        entityManager=entityManagerFactory.createEntityManager();
        Package oldPackage = getPackageFromID(id);
        Status s = oldPackage.getStatus();
        Integer nrBookingsOld = oldPackage.getNrOfBookings();
        Package p = new Package(id, name, price, startDate, endDate, extraDetails, capacity, nrBookingsOld, s, destination);
        entityManager.getTransaction().begin();
        entityManager.merge(p);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
