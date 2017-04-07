package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Role;
import entity.User;
import facades.AdminFacade;
import interfaces.AdminFacadeInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import security.PasswordStorage;

@Path("admin")
//@RolesAllowed("Admin")
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
    public String addUser(String user) throws PasswordStorage.CannotPerformOperationException {
        
        entity.User userToAdd = gson.fromJson(user, User.class);//save user from json to java format
        userToAdd.setPassword(PasswordStorage.createHash(userToAdd.getPassword()));//Hash password
        
        
        List<Role> roles = new ArrayList();
        Role newRole = new Role("User");
        roles.add(newRole);
//        userToAdd.setRoles(userToAdd.getRoles());
//        System.out.println("hulabula: "+userToAdd.getRoles());
//        for (Role role : userToAdd.getRoles()) {
//            
//            userToAdd.setRoles(userToAdd.getRoles());
//        }
//        userToAdd.setRoles(userToAdd.getRoles());
        //System.out.println("roles: "+userToAdd.getRoles());
        
//        rolle på user
//        user på rolle
//        persist user
        
//        userToAdd.setRoles( userToAdd.getRoles());
//            userToAdd.setRoles( roles);
            userToAdd.setRoles( roles);

        
        entity.User addedUser = ad.createUser(userToAdd);//add user to database
        return gson.toJson(addedUser);//return book in json format
//        return gson.toJson(user);//return book in json format
    }
}
