
package interfaces;

import entity.Book;
import java.util.List;


public interface BookFacadeInterface {
    
    //Create
    public Book createBook(Book book);
    
    //Read One Book (To rule them all)
    public Book readBook(int id);
    
    //Read All
    public List<Book> readBooks();
    
    
    //UPDATE
    public Book updateBook(Book book);
    
    
    //DELETE
    public Book deleteBook(int id);
    
}
