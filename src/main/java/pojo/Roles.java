package pojo;

import java.util.ArrayList;
import java.util.List;

public class Roles {

    public List<pojo.Role> roles = new ArrayList();
    
    public Roles(List<entity.Role> lr) {
        for (entity.Role r : lr) {
            this.roles.add(new pojo.Role(r));
        }
    }
}