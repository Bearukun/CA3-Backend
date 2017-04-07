package pojo;

import java.util.ArrayList;
import java.util.List;

public class Role {

   // public String userName;
    public String roleName;
    public List<String> users = new ArrayList();
    
    public Role(entity.Role r) {
        
        this.roleName = r.getRoleName();
        
        for (entity.User u : r.getUsers()) {
            this.users.add(u.getUserName());
        }
    }
}