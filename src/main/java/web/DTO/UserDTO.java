package web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import web.model.Role;

import java.util.Set;

@Data
public class UserDTO {
    private String username;
    private String lastname;
    private long id;
    private String password;
    private String email;
    private Set<Role> roles;
}
