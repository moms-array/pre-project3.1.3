package web.service;


import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getListUser();
    Optional<User> getById(long id);
    void add(User user);
    void delete(User user);
    void edit(User user);
}
