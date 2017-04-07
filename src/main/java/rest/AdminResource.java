package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.User;
import facades.AdminFacade;
import interfaces.AdminFacadeInterface;
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

@Path("admin")
@RolesAllowed("Admin")
public class AdminResource {
  
    AdminFacade ad;
  Gson gson = new GsonBuilder().setPrettyPrinting().create();  
    
  public AdminResource(){
          ad = new AdminFacade();
  }
  
  @GET
  @Path("all")
  @Produces(MediaType.APPLICATION_JSON)
  public String getUsers(){
      List<entity.User> users = ad.readUsers();
      return gson.toJson(new pojo.Users(users));
  } 

  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    return "{\"message\" : \"Hello Admin from server (call accesible by only authenticated ADMINS)\",\n"+"\"serverTime\": \""+now +"\"}"; 
  }
  
  @DELETE
    @Path("delete/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("userName") String userName) {
        
        JsonObject deletedUser =  ad.deleteUser(userName);
       
        return gson.toJson(deletedUser);
    }
    
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String user) {
        entity.User userToAdd = gson.fromJson(user, User.class);//save book from json to java format
//        System.out.println("book to add title: "+bookToAdd.getTitle());
//        System.out.println("book to add id: "+bookToAdd.getId());
        entity.User addedUser = ad.createUser(userToAdd);//add book to database
//        System.out.println("added book title: "+addedBook.getTitle());
//        System.out.println("added book id: "+addedBook.getId());
        return gson.toJson(addedUser);//return book in json format
    }
}
