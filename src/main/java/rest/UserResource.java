package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Book;
import entity.User;
import facades.AdminFacade;
import facades.BookFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
//@RolesAllowed("User")
public class UserResource {
    BookFacade bf;
    AdminFacade ad;
    Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
  
    public UserResource() {
        bf = new BookFacade();
        ad = new AdminFacade();
    }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getUsers(){
      return gson.toJson(ad.readUsers());
  }  
  
  @GET
  @Path("footballclubs")
  @Produces(MediaType.APPLICATION_JSON)
  public String getClubs(){
    return "[{\"name\":\"Ten Scout Bastards\", \"url\":\"http://www.liverpoolfc.com\"},{\"name\":\"Manchester United\",\"url\" : \"http://www.manutd.com/\"}]"; 
  }
 
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addBook(String inputtedBook) {
        entity.Book bookToAdd = gson.fromJson(inputtedBook, Book.class);//save book from json to java format
//        System.out.println("book to add title: "+bookToAdd.getTitle());
//        System.out.println("book to add id: "+bookToAdd.getId());
        entity.Book addedBook = bf.createBook(bookToAdd);//add book to database
//        System.out.println("added book title: "+addedBook.getTitle());
//        System.out.println("added book id: "+addedBook.getId());
        return gson.toJson(addedBook);//return book in json format
    }
    
    @POST
    @Path("edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editBook(String inputtedBook) {
        Book bookToEdit = gson.fromJson(inputtedBook, Book.class);//save book from json to java format
        Book editedBook = bf.updateBook(bookToEdit);//edit book in database
        return gson.toJson(editedBook);//return book in json format
    }
  
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteBook(@PathParam("id") int id) {
        Book deletedBook = bf.deleteBook(id);
        return gson.toJson(deletedBook);
    }
}