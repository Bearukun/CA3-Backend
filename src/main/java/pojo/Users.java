package pojo;

import entity.Role;
import java.util.ArrayList;
import java.util.List;

public class Users {

    public List<pojo.User> users = new ArrayList();
    
    public Users(List<entity.User> lu) {
        for (entity.User u : lu) {
            this.users.add(new pojo.User(u));
        }
    }
}