package sample.repository;

import sample.model.Destination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DestinationRepository {
    public static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    EntityManager em=entityManagerFactory.createEntityManager();

    public void insertDestination(Destination destination){
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteDestination(){

    }

    public List<Destination> selectAllDestinations() {
        Query query = em.createQuery("SELECT d FROM Destination d");
        java.util.List<Destination> resultList = query.getResultList();
        return resultList;

    }
}
