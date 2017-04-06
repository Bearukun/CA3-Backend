package interfaces;

import com.google.gson.JsonObject;
import entity.User;
import java.util.List;

public interface AdminFacadeInterface {
    
    //Create
    public User createUser(User user);
    
    //Read All
    public List<User> readUsers();
    
    //UPDATE
    public JsonObject updateUser(User user);
    
    //DELETE
    public JsonObject deleteUser(String userName);
    
}
