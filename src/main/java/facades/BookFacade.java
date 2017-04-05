
package facades;

import entity.Book;
import interfaces.BookFacadeInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class BookFacade implements BookFacadeInterface{

      EntityManagerFactory emf;
      EntityManager em;

    public BookFacade() {
        emf = Persistence.createEntityManagerFactory("pu_development");
    }
      
      
    
    @Override
    public Book createBook(Book book) {
        em = emf.createEntityManager();
        boolean dataAddedOK = persistData(book, em);//add book and save+check boolean for status
        Book addedBook = em.find(Book.class,book.getId());//find book with parameter id
        if(addedBook!=null){
            return addedBook;
        }
        return null;
    }
    
    @Override
    public Book updateBook(Book book) {
        em = emf.createEntityManager();
        
        System.out.println(book.getId() + "Hallllllo!!!!");
        
        
        
        boolean dataEditedOK = mergeData(book, em);//add book and save+check boolean for status
        
         em = emf.createEntityManager();
        
        Book editedBook = em.find(Book.class,book.getId());//find book with parameter id
        if(editedBook!=null){
            return editedBook;
        }
        return null;
    }

    @Override
    public Book readBook(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> readBooks() {
        
        em = emf.createEntityManager();
        
        Query q1 = em.createQuery("select b from Book b", Book.class);
        
        
        
        
        return q1.getResultList();
        
    }

  
    @Override
    public Book deleteBook(int id) {
        em = emf.createEntityManager();
        Book bookToBeDeleted = em.find(Book.class,id);//find book with parameter id
        boolean dataDeletedOK = deleteData(bookToBeDeleted, em);//delete book and save+check boolean for status
        if(dataDeletedOK){
            return bookToBeDeleted;
        }
        return null;
    }
    
    private boolean persistData(Object o, EntityManager em){
        try{
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        }
        catch(Exception e){
            return false;
        }
        finally{
            em.close();
        }
        return true;
    }
    
    private boolean deleteData(Object o, EntityManager em){
        try{
            em.getTransaction().begin();
            em.remove(o);
            em.getTransaction().commit();
        }
        catch(Exception e){
            return false;
        }
        finally{
            em.close();
        }
        return true;
    }
    
     private boolean mergeData(Object o, EntityManager em){
        try{
            em.getTransaction().begin();
            em.merge(o);
            em.getTransaction().commit();
        }
        catch(Exception e){
            return false;
        }
        finally{
            em.close();
        }
        return true;
    }
    
}
