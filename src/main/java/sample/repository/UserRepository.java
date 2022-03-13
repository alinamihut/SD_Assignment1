package sample.repository;

import sample.model.User;

import javax.persistence.*;
import java.util.List;

public class UserRepository {
    public static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    //EntityManager entityManager=entityManagerFactory.createEntityManager();

    public void insertUser(User user){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }
    public List<User> findAllUsers() {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM User e");
         List <User> resultList = query.getResultList();
        return resultList;
    }

    public int getNextAvailableID (){
        return findAllUsers().size() + 1;
}
    public boolean checkEmailAlreadyExists(String email){
        List<User> users = findAllUsers();

        for (User u: users){
            if (email.equals(u.getEmail()))
                return true;
        }
        return false;
    }

    public String findPasswordForUser(String s){
        try {
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            User user = entityManager.createQuery(
                    "SELECT u from User u WHERE u.email = :email", User.class).
                    setParameter("email", s).getSingleResult();
            return user.getPassword();
        }
        catch(NoResultException e){
            return null;
        }
    }
    }
