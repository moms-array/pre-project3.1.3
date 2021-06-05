package web.service;

import web.DTO.UserDTO;
import web.model.User;

import java.util.List;

public interface UserService {
    UserDTO findUserById(Long id);
    void update(UserDTO userDTO);
    void deleteUser(Long id);
    List<UserDTO> userList();
    void addUser(UserDTO userDTO);
    User findByUserName(String name);

}
