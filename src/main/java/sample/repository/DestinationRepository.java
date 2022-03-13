package sample.repository;

import sample.model.Destination;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

public class DestinationRepository {
    public static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");


    public void insertDestination(String destinationName){
        int id = getNextAvailableIDForDestination();
        Destination destination  = new Destination(id, destinationName);
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }

    @PersistenceUnit
    public Destination searchDestinationFromName(String destinationName){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.joinTransaction();
        try {


            Destination destination = em.createQuery(
                    "SELECT d from Destination d WHERE d.destinationName = :destinationName", Destination.class).
                    setParameter("destinationName",destinationName).getSingleResult();
            em.getTransaction().commit();
            em.close();
            return destination;
        }
        catch(NoResultException e){
            return null;
        }
    }

    public String getDestinationIDFromName(String destinationName){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.joinTransaction();
        try {

            Destination destination = em.createQuery(
                    "SELECT d from Destination d WHERE d.destinationName = :destinationName", Destination.class).
                    setParameter("destinationName",destinationName).getSingleResult();
            em.getTransaction().commit();
            em.close();
            return String.valueOf(destination.getId());
        }
        catch(NoResultException e){
            return null;
        }
    }
    @Transactional
    public void deleteDestination(String destinationName){
        EntityManager em=entityManagerFactory.createEntityManager();
        Destination d = searchDestinationFromName(destinationName);
        em.getTransaction().begin();
        em.joinTransaction();
        String id = getDestinationIDFromName(destinationName);
        em.createQuery("DELETE FROM Destination WHERE destinationName = :destinationName").setParameter("destinationName", destinationName)
                .executeUpdate();
        em.createQuery("DELETE FROM Package WHERE id= :destinationID").setParameter("destinationID", Integer.parseInt(id) )
                .executeUpdate();


        em.getTransaction().commit();
        em.close();

    }

    public int getNextAvailableIDForDestination (){
        return selectAllDestinations().size() + 2;
    }


    public List<Destination> selectAllDestinations() {
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT d FROM Destination d");
        java.util.List<Destination> resultList = query.getResultList();
        em.close();
        return resultList;

    }
}
