package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Book;
import facades.AdminFacade;
import facades.BookFacade;
import interfaces.AdminFacadeInterface;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("all")
public class AllResource {

  BookFacade bf;
  AdminFacadeInterface ad;
  Gson gson = new GsonBuilder().setPrettyPrinting().create();  
  
  @Context
  private UriInfo context;

  /**
   * Creates a new instance of A
   */
  public AllResource() {
         bf = new BookFacade();
         ad = new AdminFacade();
    }

  /**
   * Retrieves representation of an instance of rest.All
   * @return an instance of java.lang.String
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getText() {
    return " {\"message\" : \"result for all\"}";
  }
  
  @GET
  @Path("allbooks")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAllBooks() {
      List<entity.Book> b1 = bf.readBooks();//Get all books from database, via facade
      return gson.toJson(b1);//Convert list of books to json and return
  }
//  
//  @DELETE
//  @Path("delete/{id}")
//  @Produces(MediaType.APPLICATION_JSON)
//  public Book deleteBook(@PathParam("id") int id) {
//      Book deletedBook = bf.deleteBook(id);
//      return deletedBook;
//  }
//  
////  @POST
////  @Path("add/{book}")
////  @Produces(MediaType.APPLICATION_JSON)
////  public Book deleteeBook(@PathParam("id") int id) {
////      Book deletedBook = bf.deleteBook(id);
////      return deletedBook;
////  }
//  
//  @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String addBook(String inputtedBook) {
//        Book bookToAdd = gson.fromJson(inputtedBook, Book.class);
//        //add book to database
//        Book addedBook = bf.createBook(bookToAdd);
//        //return book in json format
//        return gson.toJson(addedBook);
//    }
//    
//    
//      /**
//   * ONLY FOR TESTING THE ADMIN FACADE! 
//   * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//   */
//  @GET
//  @Path("hejsa")
//  @Produces(MediaType.APPLICATION_JSON)
//  public String getUsers(){
//      
//      return gson.toJson(ad.readUsers());
//      
//  }
//    
//    
//    @POST
//    @Path("edit")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String editBook(String inputtedBook) {
//        
//        Book bookToEdit = gson.fromJson(inputtedBook, Book.class);
//        //edit book in database
//        Book editedBook = bf.updateBook(bookToEdit);
//        //return book in json format
//        return gson.toJson(editedBook);
//    }
}
