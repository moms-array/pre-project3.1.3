package web.repository;

import web.model.User;

import java.util.List;

public interface UserRepository{
    List<User> getListUser();
    User getById(int id);
    void add(User user);
    void delete(User user);
    void edit(User user);
}
