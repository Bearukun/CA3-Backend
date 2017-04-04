package rest;

import entity.Book;
import facades.BookFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
@RolesAllowed("User")
public class User {
    BookFacade bf;
  
    public User() {
        bf = new BookFacade();
    }
    
  @GET
  @Path("demouser")
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "{\"message\" : \"Hello User from Server (Accesible by only authenticated USERS)\"}"; 
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