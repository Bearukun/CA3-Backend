package utils;

import entity.Role;
import entity.User;
import entity.Book;
import facades.BookFacade;
import facades.UserFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.New;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class makeTestBooks {

  //Only for initial testing REMOVE BEFORE PRODUCTION
  //Run this file to setup the users required to use the initial version of the seed
  public static void main(String[] args) {
    EntityManager em = Persistence.createEntityManagerFactory("pu_development").createEntityManager();
    try {
      System.out.println("Creating Books ");
      
      BookFacade bf = new BookFacade();
//          em.getTransaction().begin();
       Book b1= new Book("Title 1", "Info for book title1", "way moreInfo for book title 1");
       Book b2= new Book("Title 2", "Info for book title2", "way moreInfo for book title 2");
       Book b3= new Book("Title 3", "Info for book title3", "way moreInfo for book title 3");
       Book b4= new Book("Title 4", "Info for book title4", "way moreInfo for book title 4");
       Book b5= new Book("Title 5", "Info for book title5", "way moreInfo for book title 5");
       Book b6= new Book("Title 6", "Info for book title6", "way moreInfo for book title 6");
   
      
       
        //em.persist(b1);
        
       
     
            bf.createBook(b1);
            bf.createBook(b2);
            bf.createBook(b3);
            bf.createBook(b4);
            bf.createBook(b5);
            bf.createBook(b6);
         
        
        
    
        
        
        //em.getTransaction().commit();
        System.out.println("Created Books");
      
    } catch (Exception ex) {
      Logger.getLogger(BookFacade.class.getName()).log(Level.SEVERE, null, ex);
      em.getTransaction().rollback();
    } finally {
      //em.close();
    }
  }
}