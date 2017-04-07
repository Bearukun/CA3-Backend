package facades;

import com.google.gson.JsonObject;
import entity.Role;
import entity.User;
import interfaces.AdminFacadeInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AdminFacade implements AdminFacadeInterface {

    EntityManagerFactory emf;
    EntityManager em;

    public AdminFacade() {
        emf = Persistence.createEntityManagerFactory("pu_development");
    }

    /**
     * Method to create a user.
     *
     * @param user The user that must be persisted to the database.
     * @return The user object added.
     */
    @Override
    public User createUser(User user) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            //If something goes wrong, rollback. 
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }
    
    
    
    
    
    public pojo.User createPojoUser(pojo.User user) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            //If something goes wrong, rollback. 
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }
    
    
    

    /**
     * Method to retrieve all persons from the database.
     *
     * @return A list of User-objects.
     */
    @Override
    public List<User> readUsers() {
        em = emf.createEntityManager();
//        Query q1 = em.createQuery("select u from SEED_USER u", User.class);
        TypedQuery<User> q1 = em.createQuery("SELECT u FROM SEED_USER u",User.class);
        return q1.getResultList();
    }

    /**
     * Method to update a user
     * @param user User object that has been edited
     * @return Returns a custom jsonObject that tells the user has been edited. 
     */
    @Override
    public JsonObject updateUser(User user) {
        em = emf.createEntityManager();
        JsonObject jo = new JsonObject();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            jo.addProperty("error", ex.getMessage());
        }
        jo.addProperty("message", "User has been edited");
        return jo;
    }

    /**
     * Method used to delete a user from the database.
     * @param userName Id of the user that needs to be removed. 
     * @return Returns a json object that tells that the user has been removed.
     */
    @Override
    public JsonObject deleteUser(String userName) {
        em = emf.createEntityManager();
        User user = null;
        try {
            em.getTransaction().begin();
            user = em.merge(em.find(User.class, userName));
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("message", "User: " + user.getUserName() + " with user name: " + userName + " has been removed.");
        return jo;
    }
}