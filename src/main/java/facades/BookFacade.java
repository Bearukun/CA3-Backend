
package facades;

import entity.Book;
import interfaces.BookFacadeInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BookFacade implements BookFacadeInterface{

      EntityManagerFactory emf;
      EntityManager em;

    public BookFacade() {
        emf = Persistence.createEntityManagerFactory("pu_development");
    }
      
      
    
    @Override
    public Book createBook(Book book) {
     //Book b1= new Book("Title 2", "Info for book title2", "way moreInfo for book title 2");
        em = emf.createEntityManager();
        persistData(book, em);
        return book;
    }

    @Override
    public Book readBook(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> readBooks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book updateBook(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book deleteBook(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    
}
