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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
 
  @DELETE
  @Path("delete")
  @Produces(MediaType.APPLICATION_JSON)
  public void deleteBook() {
    bf.deleteBook(1);
  }
}