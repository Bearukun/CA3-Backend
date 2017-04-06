package pojo;

import entity.Role;
import java.util.ArrayList;
import java.util.List;

public class User {

    public String username;
    public List<String> roles = new ArrayList();
    
    public User(entity.User u) {
        this.username = u.getUserName();
        for (Role role : u.getRoles()) {
            this.roles.add(role.getRoleName());
        }
    }
}