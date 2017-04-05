package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import facades.AdminFacade;
import interfaces.AdminFacadeInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demoadmin")
@RolesAllowed("Admin")
public class Admin {
  
    AdminFacade ad;
  Gson gson = new GsonBuilder().setPrettyPrinting().create();  
    
  public Admin(){
          ad = new AdminFacade();
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    
    //Get all books from database
     
      List<User> u1 = ad.readUsers();
      
      //Convert list of books to json
      //Will return json data
      return gson.toJson(u1);
    
//    return "{\"message\" : \"Hello Admin from server (call accesible by only authenticated ADMINS)\",\n"+"\"serverTime\": \""+now +"\"}"; 
  }
 
  @GET
  @Path("all")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAllBooks() {
      
      //Get all books from database
     
      List<User> u1 = ad.readUsers();
      
      //Convert list of books to json
      //Will return json data
      return gson.toJson(u1);
      
      
  }
  
}
