package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);
    void update(User user);
    void deleteUser(Long id);
    List<User> userList();
    void addUser(User user);
    User findByUserName(String name);

}
